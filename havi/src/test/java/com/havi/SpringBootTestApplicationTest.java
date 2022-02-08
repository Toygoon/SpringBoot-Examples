package com.havi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// To operate @SpringBootTest properly, @RunWith annotation must be annotated
@RunWith(SpringRunner.class)
@SpringBootTest(value = "value=test",
        // classes : Specify the class
        // webEnvironment : Run Mock servlet
        classes = {SpringBootTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootTestApplicationTest {

    // value : Set the property to apply before the tests
    // This overrides the default property
    @Value("${value}")
    private String value;

    // properties : Add the property before the tests
    // The structure of property is {key=value} like tuples
    /*
    @Value("${property.value}")
    private String propertyValue;
     */

    @Test
    void contextLoads() {
        assertThat(value, is("test"));
        //assertThat(propertyValue, is("propertyTest"));
    }

}
