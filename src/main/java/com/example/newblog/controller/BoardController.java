package com.example.newblog.controller;

import com.example.newblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 메인페이지가 호출될 때 블로그 목록을 조회해서 가져간다.
    // @PageableDefault 로 페이징 처리
    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable)); // index페이지에서 ${boards}로 값을 읽을 수 있다.
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String writeForm(){
        return "board/writeForm";
    }


    /** 글 상세 보기 **/
    @GetMapping("/board/detail/{id}")
    public String readDetail(@PathVariable int id, Model model) {
        model.addAttribute("board",boardService.글상세보기(id));
        return "/board/detailForm";
    }

    @GetMapping("/board/{id}/modifyForm")
    public String modifyForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/modifyForm";
    }
}
