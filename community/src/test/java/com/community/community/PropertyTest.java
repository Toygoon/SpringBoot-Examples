package com.community.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
/* Code provided from the book is not working, here's a quick fix and reasons
* @ComponentScan : The component couldn't be recognized properly, so define this and scan it manually
* @EnableConfigurationProperties : This is needed to enable configurations from recognized components
*
* */
@ComponentScan
@EnableConfigurationProperties(FruitProperty.class)
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
