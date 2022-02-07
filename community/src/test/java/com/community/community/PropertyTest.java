package com.community.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyTest {
    // Instance; Get list from FruitProperty
    @Autowired
    FruitProperty fruitProperty;

    @Test
    public void test() {
        // Getter auto generated with @Data annotation
        List<Map> fruitData= fruitProperty.getList();

        assertThat(fruitData.get(0).get("name"), is("banana"));
        assertThat(fruitData.get(0).get("color"), is("yellow"));

        assertThat(fruitData.get(1).get("name"), is("apple"));
        assertThat(fruitData.get(1).get("color"), is("red"));

        assertThat(fruitData.get(2).get("name"), is("water melon"));
        assertThat(fruitData.get(2).get("color"), is("green"));
    }
}
