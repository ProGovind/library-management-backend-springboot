package com.example.minor_1.Service;

import com.example.minor_1.exceptions.TxnServiceException;
import com.example.minor_1.models.Book;
import com.example.minor_1.models.Student;
import com.example.minor_1.models.Transaction;
import com.example.minor_1.models.TransactionType;
import com.example.minor_1.repository.TransactionRepository;
import com.example.minor_1.request.BookFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TxnService {

    public  static Logger logger = (Logger) LoggerFactory.getLogger(TxnService.class);
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Value("${book.return.due_date}")
    int number_of_days;

    public String issueTxn(int studentId, int bookId) throws TxnServiceException {

        logger.info("Issue request came: studentId - {}, bookId - {}", studentId, bookId);

        Student student = studentService.findStudentByStudentId(studentId);

        if(student == null)
        {
            throw new TxnServiceException("Student is not present in the library");
        }

        List<Book> books = bookService.find(BookFilter.BOOK_ID ,String.valueOf(bookId));

        if(books == null || books.size() != 1 || books.get(0).getStudent() !=null)
        {
            throw new TxnServiceException("Book is not present in Library");
        }

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .student(student)
                .payment(books.get(0).getCost())
                .transactionType(TransactionType.ISSUE)
                .book(books.get(0)).build();

        transactionRepository.save(transaction);

        books.get(0).setStudent(student);
        bookService.createOrUpdate(books.get(0));

        return transaction.getExternalTxnId();

    }

    public String returnTxn(int studentId, int bookId) throws TxnServiceException {

        Student student = studentService.findStudentByStudentId(studentId);

        if(student == null)
        {
            throw new TxnServiceException("Student is not present in the library");
        }

        List<Book> books = bookService.find(BookFilter.BOOK_ID ,String.valueOf(bookId));

        if(books == null || books.size() != 1)
        {
            throw new TxnServiceException("Book is not present in Library");
        }

        if(books.get(0).getStudent().getId() != studentId)
        {
            throw new TxnServiceException("Book is not issued to this student");
        }

        Transaction issutxn = transactionRepository
                .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(books.get(0) , student,TransactionType.ISSUE);
        logger.info("Issue txn date - {}, txnId - {}", issutxn.getTransactionDate(), issutxn.getId());

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .payment(calculateFine(issutxn))
                .book(books.get(0))
                .transactionType(TransactionType.RETURN)
                .student(student)
                .build();

        transactionRepository.save(transaction);

        books.get(0).setStudent(null);
        bookService.createOrUpdate(books.get(0));
        return transaction.getExternalTxnId();
    }

    private double calculateFine(Transaction s) {

        long issueTime = s.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issueTime;
        long dayPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);

        logger.info("Number of Days For Fine Calculation - {}",number_of_days);

        if(dayPassed >= number_of_days)
        {
            return (dayPassed-number_of_days) * 1.0;
        }
return 0.0;
    }
}
