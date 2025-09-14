package com.example.minor_1.controllers;

import com.example.minor_1.Service.TxnService;
import com.example.minor_1.exceptions.TxnServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TxnService txnService;

    @PostMapping("/transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId,
                            @RequestParam("BookId") int bookId) throws TxnServiceException,InterruptedException{
        return txnService.issueTxn(studentId , bookId);
    }

    @PostMapping("/transaction/return")
    public String returnTxn(@RequestParam("studentId") int studentId , @RequestParam("bookId") int bookId ) throws TxnServiceException, InterruptedException
    {
        return txnService.returnTxn(studentId,bookId);
    }
}
