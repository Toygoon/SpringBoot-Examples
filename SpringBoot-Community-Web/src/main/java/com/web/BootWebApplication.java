package com.web;

import com.web.domain.Board;
import com.web.domain.enums.BoardType;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import com.web.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class BootWebApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(BootWebApplication.class, args);
    }

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    /* addArgumentResolvers : Override a method to create new annotations */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    /* CommandLineRunner is the interface to run some codes after running application
    * @Bean : Dependency Injection Mechanism, This registers "CommandLineRunner" as Bean,
    *       be injected "UserRepository" and "BoardRepository" */
    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository)
            throws Exception {
        return args -> {
            User user = userRepository.save(User.builder()
                    .name("havi")
                    .password("test")
                    .email("test@gmail.com")
                    .createdDate(LocalDateTime.now())
                    .build());

            // Save 1 to 200 ranges with forEach statements
            IntStream.rangeClosed(1, 200).forEach(index -> {
                boardRepository.save(Board.builder()
                        .title("Title " + index)
                        .subTitle("Num " + index)
                        .content("Content")
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user)
                        .build());
            });
        };
    }
}