package com.havi.service;

import com.havi.domain.Book;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/* RestTemplate for asynchronous querying specified URL service */
@Service
public class BookRestService {
    private final RestTemplate restTemplate;

    public BookRestService(RestTemplateBuilder restTemplateBuilder) {
        // Generate RestTemplate using RestTemplateBuilder; to use provided other settings
        this.restTemplate = restTemplateBuilder.rootUri("/rest/test").build();
    }

    public Book getRestBook() {
        // Request to "/rest/test" URI using getForObject method; Get response for Book object
        return this.restTemplate.getForObject("/rest/test", Book.class);
    }
}
