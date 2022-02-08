package com.havi.controller;

import com.havi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    // HTTP protocol to "/books" with "GET"
    @GetMapping("/books")
    public String getBookList(Model model) {
        // Get bookList key (list) from bookService
        model.addAttribute("bookList", bookService.getBookList());

        return "book";
    }
}
