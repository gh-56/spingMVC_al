package com.erser.springmvc.dto;

import com.erser.springmvc.entity.Article;
import com.erser.springmvc.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;    // 댓글 아이디 PK
    private Long articleId; // 게시글 아이디 FK
    private String nickname;
    private String body;

    // DTO 생성 메서드 (Entity -> DTO)
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getArticle().getId(), comment.getNickname(), comment.getBody());
    }
}
