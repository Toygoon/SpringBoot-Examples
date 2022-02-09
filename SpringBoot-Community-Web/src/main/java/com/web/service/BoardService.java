package com.web.service;

import com.web.domain.Board;
import com.web.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// @Service : This is a service
@Service
public class BoardService {
    public final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        // If : pageNumber <= 0, reset pageNumber to 0
        // Else : Create new PageRequest object which has a 10 pages by default
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ?
                        0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx) {
        // Return board object by using idx from board
        return boardRepository.findById(idx).orElse(new Board());
    }
}
