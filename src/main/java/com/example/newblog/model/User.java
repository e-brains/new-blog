package com.example.newblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 자동으로 테이블 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id  // 프라이머리 키가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB의 넘버링 전략을 따른다. (자동입력)
    private int id; // 시퀀스(오라클) , auto_increment (mysql)

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 100) // 암호화 때문에 길이를 넉넉하게 준다.
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)  // DB는 RoleType이 없기 때문에 해당 enum이 String이라고 알려 줘야 한다.
    private RoleType role;  // Enum을 사용하는 것이 좋다. RoleType 범위로 강제된다.

    @CreationTimestamp // 시간이 자동으로 입력된다. (자동입력)
    private Timestamp createDate;

}
