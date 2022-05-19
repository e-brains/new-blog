package com.example.newblog.service;

import com.example.newblog.model.Board;
import com.example.newblog.model.Reply;
import com.example.newblog.model.User;
import com.example.newblog.repository.BoardRepository;
import com.example.newblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 : Ioc 해줌
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;


    @Transactional
    public void 글쓰기저장(Board board, User user) {

        board.setCount(0);  // 최초 조회수는 0이므로 0셋팅
        // user 정보는 시큐리티 세션의 PrincipalDetail에서 추출한  User객체를 넘겨받는다.
        board.setUser(user);

        boardRepository.save(board);

    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        // board 모델은 reply를 mappedBy로 들고 있으므로 별도의 댓글 조회를 하지 않아도 된다.
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void 글삭제(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정(int id, Board requestBoard){

        // 수정하려면 영속화를 먼저 시켜야 한다.
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 서비스 종료 시 즉, 트랜잭션이 종료될때 더티 체킹이 일어나면서 자동 update가 된다.
        //boardRepository.save(board);
    }

    @Transactional
    public void 댓글쓰기저장(int boardId, Reply requestReply, User user){

        Board board = boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 저장 실패 : 게시글의 id를 찾을 수 없습니다.");
        }); // 영속화 완료

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);

    }

}
