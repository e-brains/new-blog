package com.example.newblog.repository;

import com.example.newblog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	// DTO를 만들어서 처리할 때 이렇게 직접 쿼리를 작성해서 편리하게 사용할 수 있다.
	// 과거의 데이터 스트럭쳐를 만들어서 주고 받듯이 버퍼(여기서는 DTO)를 만들어서 한방에 처리할 수 있다.
	@Modifying  // insert , update 시에 사용
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.  void 생략 가능
}
