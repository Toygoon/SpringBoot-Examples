package com.community.community;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/* The more oriented object style mapping rather than @Value */
// Lombok settings, this auto generates the getter and setter at compiling
@Data
// To use @ConfigurationProperties annotation, @Component annotation muse be defined
@Component
// Bind to the list as "fruit"
@ConfigurationProperties("fruit")
public class FruitProperty {
    private List<Fruit> list;
}
