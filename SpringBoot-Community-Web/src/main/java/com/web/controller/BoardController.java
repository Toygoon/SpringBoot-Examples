package com.web.controller;

import com.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// Set API URI to "/board"
@RequestMapping("/board")
public class BoardController {
    // To inject boardService dependency
    @Autowired
    private BoardService boardService;

    // Set more mapping urls like array
    @GetMapping({"", "/"})
    /* Get "Primary Key" using @RequestParam annotation
    If there's no pre-bind value, the value will set by "0" which is default value
    When findBoardById(idx) with 'idx = 0', null value will be returned */
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx,
                        Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));

        return "/board/form";
    }

    @GetMapping("/list")
    // @PageableDefault : To set the protocols including size, sort, and direction
    public String list (@PageableDefault Pageable pageable, Model model) {
        // Set a view about target
        model.addAttribute("boardList", boardService.findBoardList(pageable));

        return "/board/list";
    }
}
