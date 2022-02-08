package com.havi.service;

import com.havi.domain.Book;

import java.util.List;

public interface BookService {
    /* Abstract method for send the list of Book type */
    List<Book> getBookList();
}
