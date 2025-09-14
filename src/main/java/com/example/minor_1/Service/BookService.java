package com.example.minor_1.Service;

import com.example.minor_1.models.Genre;
import com.example.minor_1.request.BookFilter;
import jakarta.validation.Valid;
import com.example.minor_1.models.Author;
import com.example.minor_1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.minor_1.repository.AuthorRepository;
import com.example.minor_1.repository.BookRepository;
import com.example.minor_1.request.BookCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    public void createOrUpdate(@Valid BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.to();
        createOrUpdate(book);
    }

    public void createOrUpdate(Book book)
    {
        Author author = book.getAuthor();

        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null)
        {
            authorFromDB = authorRepository.save(author);
        }

        book.setAuthor(authorFromDB);
        bookRepository.save(book);

    }

    public List<Book> find(BookFilter bookFilterType , String value)
    {
        switch(bookFilterType)
        {
            case NAME : return bookRepository.findByName(value);

            case AUTHOR_NAME: return bookRepository.findByAuthor_Name(value);

            case GENRE: return bookRepository.findByGenre(Genre.valueOf(value));

            case BOOK_ID:
                return bookRepository
                        .findAllById(Collections.singletonList(Integer.parseInt(value)));

            default: return new ArrayList<>();
        }

    }
}
