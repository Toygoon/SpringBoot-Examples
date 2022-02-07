package com.community.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoConfigurationApplicationTests {
    // Map the sub value of the root values with '.'
    @Value("${property.test.name}")
    private String propertyTestName;

    // Map the single value key
    @Value("${propertyTest}")
    private String propertyTest;

    // If the key not exists in YAML, map the default value with this annotation
    @Value("${noKey:default value}")
    private String defaultValue;

    // Map like array when the values are more than single one
    @Value("${propertyTestList}")
    private String[] propertyTestArray;

    // Write down with SpEL, this maps to the List as delim ','
    @Value("#{'${propertyTestList}'.split(',')}")
    private List<String> propertyTestList;

    @Test
    public void testValue() {
        // Test the value if it's equal with defined value from "application.yml"
        assertThat(propertyTestName, is("property depth test"));
        assertThat(propertyTest, is("test"));
        assertThat(defaultValue, is("default value"));

        /* Experimental
        * Is the test succeeded with new defined values? : It works!
        */
        final String[] newDefinedArray = {"a", "b", "c"};
        final List<String> newDefinedList = new ArrayList<>(Arrays.asList(newDefinedArray));

        assertThat(propertyTestArray, is(newDefinedArray));
        assertThat(propertyTestArray[0], is("a"));
        assertThat(propertyTestArray[1], is("b"));
        assertThat(propertyTestArray[2], is("c"));

        assertThat(propertyTestList, is(newDefinedList));
        assertThat(propertyTestList.get(0), is("a"));
        assertThat(propertyTestList.get(1), is("b"));
        assertThat(propertyTestList.get(2), is("c"));
    }
}
