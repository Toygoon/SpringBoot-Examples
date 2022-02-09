package com.web;

import com.web.domain.Board;
import com.web.domain.BoardType;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class BootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWebApplication.class, args);
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