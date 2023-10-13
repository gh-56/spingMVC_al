package com.erser.springmvc.dto;

import com.erser.springmvc.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO : Data Transfer Object 데이터 전달 객체
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;

    // DTO를 Entity로 변환
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
