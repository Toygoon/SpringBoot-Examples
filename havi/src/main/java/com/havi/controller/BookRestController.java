package com.havi.controller;

import com.havi.domain.Book;
import com.havi.service.BookRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController : REST related annotation, used for such as JSON type responses
@RestController
public class BookRestController {
    @Autowired
    private BookRestService bookRestService;

    /* getRestBooks() return String, not JSON.
    Because @RestController is defined above. */
    @GetMapping(path = "/rest/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getRestBooks() {
        return bookRestService.getRestBook();
    }
}
