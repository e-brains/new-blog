package com.example.newblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터를 사용할때
    private String content;

    //@ColumnDefault("0") // 숫자이니 홑따옴표 필요 없음
    // 조회수는 강제로 수작업으로 넣어주기로 한다. 자동아님
    private int count;

    @ManyToOne // 연관관계를 맺어준다 (Many=Board, User=One 한명의 유저가 여러개의 글을 쓸수 있다.)
    @JoinColumn(name = "userId") // DB에 userId라는 컬럼으로 생성, fechType은 default로 EAGER로 설정됨
    private User user; // ORM에서는 조인하지 않고 오브젝트를 바로 넣는다. (DB에서는 FK로 user 테이블과 연결)

    // 하나의 게시글에 여러개의 댓글을 달수 있으므로 FK가 될수 없다.
    // Reply오브젝트에 있는 board 속성 변수명을 mappedBy한다.
    // mappedBy로 연관관계의 주인이 아님 (FK가 아님, DB에 컬럼을 만들지 않음, 나중에 조회할 때 사용, 저장시에는 신경안써도 됨)
    // @OneToMany(mappedBy = "board") // reply 테이블에 있는 board 필드명을 이용해서 reply 정보를 같이 가져와라
    // fechType은 default로 LAZY가 설정되나 여기서는 반드시 댓글정보가 필요하므로 EAGER로 변경함
    // LAZY는 필요한 경우만 조회, EAGER은 반드시 조회
    // CascadeType.REMOVE는 하위 보드이하 댓글들도 함께 삭제하라는 뜻
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"}) // jackson 라이브러리가 json 변환 시 무한참조를 못하게 방지해준다.
    @OrderBy("id desc ")
    private List<Reply> replys;

    /** 무한참조 방지 */
    // jackson 라이브러리가 board를 조회 하여 자바 오브젝트를 json으로 만들때
    // 각 프로퍼티의 값을 getter를 이용하여 읽는데 board 의 reply를 getter하고
    // reply안에 있는 board 프로퍼티를 getter할때 다시 board안에 있는 reply를 getter하면서
    // 무한참조가 일어난다. 이때 @JsonIgnoreProperties({"board"})를 하면 reply안에 있는
    // board는 getter하지 않는다.


    @CreationTimestamp
    private Timestamp createDate;

}
