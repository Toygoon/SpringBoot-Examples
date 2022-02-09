package com.havi;

import com.havi.domain.Book;
import com.havi.service.BookRestService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
// Set which Bean will be tested to avoid error
@RestClientTest(BookRestService.class)
public class BookRestTest {
    // Initialize value
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private BookRestService bookRestService;

    // Object for testing REST between client and server
    @Autowired
    private MockRestServiceServer server;

    /* Check "Response" and 'expected value' is identical
     This response will be written to "test.json", which is generated in resource folder */
    @Test
    public void testRest() {
        this.server.expect(requestTo("/rest/test"))
                .andRespond(withSuccess(new ClassPathResource("/test.json",
                        getClass()), MediaType.APPLICATION_JSON));
        Book book = this.bookRestService.getRestBook();
        assertThat(book.getTitle()).isEqualTo("_TEST");
    }

    /* Test the server returns an error
    Grab the error code with "HttpServerErrorException.class" with "ExpectedException.expect()" */
    @Test
    public void testRestError() {
        this.server.expect(requestTo("/rest/test"))
                .andRespond(withServerError());
        this.thrown.expect(HttpServerErrorException.class);
        this.bookRestService.getRestBook();
    }
}
