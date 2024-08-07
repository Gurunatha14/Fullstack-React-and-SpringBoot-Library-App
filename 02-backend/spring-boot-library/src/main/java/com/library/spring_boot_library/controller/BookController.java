package com.library.spring_boot_library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.spring_boot_library.entity.Book;
import com.library.spring_boot_library.service.BookService;
import com.library.spring_boot_library.utils.ExtractJWT;
import com.library.spring_boot_library.responsemodels.ShelfCurrentLoansResponse;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token)
            throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoans(userEmail);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean chekoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
            throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }

    @PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value = "Authorization") String token,
            @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail, bookId);

    }

    @PutMapping("/secure/renew/loan")
    public void renewLoan(@RequestHeader(value = "Authorization") String token,
            @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.renewLoan(userEmail, bookId);
    }

}
