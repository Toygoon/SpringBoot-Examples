package com.web;

import com.web.domain.Board;
import com.web.domain.enums.BoardType;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "Test title";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    /* This is the method before running the test
    * Because it is to test the generated databases */
    @Before
    public void init() {
        // Example user, board column
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());
        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("Sub Title")
                .content("Content")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build());
    }

    @Test
    public void checkGeneratedProperly() {
        // Get the user object using userRepository by email
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        // Find the writer by user object
        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("Sub Title"));
        assertThat(board.getContent(), is("Content"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }
}
