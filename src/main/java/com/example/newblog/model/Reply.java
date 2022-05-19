package com.example.newblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne  // 여러개의 답변은 하나의 게시글에 존재 (하나의 게시글에 여러개 답변이 달림)
    @JoinColumn(name = "boardId") // boardId 라는 포린키 생성
    private Board board;

    @ManyToOne   // 여러개의 답변에 하나의 유저가 존재 (하나의 유저가 여러개 답변을 달수 있음)
    @JoinColumn(name = "userId")  // userId 라는 포린키 생성
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
