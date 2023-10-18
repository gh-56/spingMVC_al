package com.erser.springmvc.entity;

import com.erser.springmvc.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor      // 기본 생성자
@AllArgsConstructor     // 전체 파라미터 생성자
@Getter
@ToString
@Entity     // 해당 클래스의 필드를 바탕으로 DB에 테이블 생성
public class Comment {
    @Id     // 대표키 PK
    @GeneratedValue     // DB에서 자동으로 숫자 증가
    private Long id;
    @ManyToOne      // 다대일 관계 ex) 게시글은 하나 댓글은 여러개 (Comment to Article)
    @JoinColumn(name = "article_id")        // 외래키FK 지정, 생략가능
    private Article article;
    @Column
    private String nickname;    // 닉네임 : 댓글 단 사람
    @Column
    private String body;        // 본문

    // dto -> entity 생성 메서드
    public static Comment createComment(CommentDto dto, Article article) {
        if(dto.getId() != null){
            throw new IllegalArgumentException("기존 댓글이 있어 댓글 생성에 실패했습니다.");
        }
        if(dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("게시글 id가 잘못되었습니다.");
        }
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패,잘못된 id입니다.");
        }
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
