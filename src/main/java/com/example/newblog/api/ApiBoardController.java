package com.example.newblog.api;

import com.example.newblog.config.auth.PrincipalDetail;
import com.example.newblog.dto.DtoResponse;
import com.example.newblog.model.Board;
import com.example.newblog.model.Reply;
import com.example.newblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApiBoardController {

    @Autowired
    private BoardService boardService;


    /**** 글쓰기 저장 ****/
    @PostMapping("/api/board/writeProc")
    public DtoResponse<String> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        System.out.println("writeProc => board.getTitle() : " + board.getTitle());

        // 서비스 호출 (시큐리티 세션의 유저정보를 추출하여 넘겨준다.
        boardService.글쓰기저장(board, principalDetail.getUser());

        return new DtoResponse<String>(HttpStatus.OK.value(), "success");
    }

    /**** 글 삭제 ****/
    @DeleteMapping("/api/board/delete/{id}")
    public DtoResponse<String> deleteById(@PathVariable int id){
        boardService.글삭제(id);
        return new DtoResponse<>(HttpStatus.OK.value(), "success");
    }

    /**** 글 수정 ****/
    @PutMapping("/api/board/modifyProc/{id}")
    public DtoResponse<String> modifyProc(@PathVariable int id, @RequestBody Board board){
        boardService.글수정(id, board);
        return new DtoResponse<>(HttpStatus.OK.value(), "success");
    }

    /**** 댓글쓰기 저장 ****/
    // 큰 프로젝트에서는 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    // 여기서 dto를 사용하지 않고 따로따로 파라미터로 받은 이유는 작은 어플리케이션이기 때문임
    // 업무단위로 모든 화면에서 공통으로 사용되는 dto , 화면단위 dto등을 만들어서 한방에 받는 것이 좋다.
    @PostMapping("/api/board/{boardId}/reply")
    public DtoResponse<String> replySave(@PathVariable int boardId ,@RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        System.out.println("replySave => reply.getContent() : " + reply.getContent());

        // 서비스 호출 (시큐리티 세션의 유저정보를 추출하여 같이 넘겨준다.
        boardService.댓글쓰기저장(boardId, reply, principalDetail.getUser());

        return new DtoResponse<String>(HttpStatus.OK.value(), "success");
    }

}
