package com.havi;

import com.havi.domain.Book;
import com.havi.repository.BookRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@ContextConfiguration
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
public class BookJpaTest {
    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookSaveTest() {
        Book book = Book.builder().title(BOOT_TEST_TITLE)
                .publishedAt(LocalDateTime.now()).build();
        /* persist() method test */
        testEntityManager.persist(book);

        assertThat(bookRepository.getOne(book.getIdx()), is(book));
    }

    @Test
    public void bookListSaveSearchTest() {
        /* Modified statements */
        Book[] book = new Book[3];
        for (int i = 0; i < book.length; i++) {
            book[i] = Book.builder().title(BOOT_TEST_TITLE + i)
                    .publishedAt(LocalDateTime.now()).build();
            testEntityManager.persist(book[i]);
        }

        /* Original statements
        Book book1 = Book.builder().title(BOOT_TEST_TITLE + "1")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);
        Book book2 = Book.builder().title(BOOT_TEST_TITLE + "2")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);
        Book book3 = Book.builder().title(BOOT_TEST_TITLE + "3")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book3);
         */

        List<Book> bookList = bookRepository.findAll();
        /* Check correct books are existed */
        assertThat(bookList, hasSize(3));
        assertThat(bookList, contains(book[1], book[2], book[3]));
    }

    @Test
    public void bookListSaveDeleteTest() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE + "1")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);
        Book book2 = Book.builder().title(BOOT_TEST_TITLE + "2")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        /* Check 2 books are deleted */
        bookRepository.deleteAll();
        assertThat(bookRepository.findAll(), IsEmptyCollection.empty());
    }
}
