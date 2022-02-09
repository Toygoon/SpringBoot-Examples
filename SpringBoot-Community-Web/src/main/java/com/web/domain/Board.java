package com.web.domain;

import com.web.domain.enums.BoardType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {
    @Id
    @Column
    /* @GeneratedValue : Auto generating serialized keys
    strategy : Database automatically manages key generation */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    /* @Enumerated : To compatible with Java enum type and database data type
    EnumType.STRING : Each data presents enum type, but saves to String type */
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    /* OneToOne is 1:1 relations
    This saves "user_idx" to the DB instead of User objects.
    fetch : There are two types; eager(Load first), and lazy(Load when use) */
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType,
                 LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }

}
