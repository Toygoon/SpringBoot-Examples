package com.havi;

import com.havi.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class BookJsonTest {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void testJson() throws Exception {
        Book book = Book.builder()
                .title("_TEST")
                .build();
        // Generate JSON format of String value to test
        String content = "{\"title\":\"_TEST\"}";

        /* Convert String to Object
        * "parseObject" converts String type value into the JSON object */
        // Check title, publishedAt
        assertThat(this.json.parseObject(content).getTitle()).isEqualTo(book.getTitle());
        assertThat(this.json.parseObject(content).getPublishedAt()).isNull();

        // Check type, title existence, title identical
        assertThat(this.json.write(book)).isEqualToJson("/test.json");
        assertThat(this.json.write(book)).hasJsonPathStringValue("title");
        assertThat(this.json.write(book)).extractingJsonPathStringValue("title")
                .isEqualTo("_TEST");


    }
}
