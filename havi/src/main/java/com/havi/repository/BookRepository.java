package com.havi.repository;

import com.havi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/* BookRepository extends JpaRepository */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
