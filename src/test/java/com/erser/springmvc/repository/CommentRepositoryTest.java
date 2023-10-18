package com.erser.springmvc.repository;

import com.erser.springmvc.entity.Article;
import com.erser.springmvc.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글을 조회")
    void findByArticleId() {
        // 4번 글 댓글 조회
        // 1. 입력 데이터 준비
        Long articleId = 4L;
        // 2. 실제 데이터
        List<Comment> actual = commentRepository.findByArticleId(articleId);
        Article article4 = new Article(4L, "인생 영화는?", "댓글 달아주세요");
        Comment comment1 = new Comment(1L, article4, "john", "기생충");
        Comment comment2 = new Comment(2L, article4, "jane", "명량");
        Comment comment3 = new Comment(3L, article4, "tom", "신과함께");
        // 3. 예상 데이터
        List<Comment> expected = Arrays.asList(comment1, comment2, comment3);
        // 4. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    @DisplayName("5번 게시글의 모든 댓글을 조회")
    void findByArticleID_5(){
        // 1. 입력 데이터 준비
        Long articleId = 5L;
        // 2. 실제 데이터
        List<Comment> actual = commentRepository.findByArticleId(articleId);
        // 3. 예상 데이터
        Article article5 = new Article(5L, "인생 음식은?", "댓글 달아주세요");
        Comment comment1 = new Comment(4L, article5, "jane", "삼겹살");
        Comment comment2 = new Comment(5L, article5, "tom", "치킨");
        Comment comment3 = new Comment(6L, article5, "john", "라면");
        List<Comment> expected = Arrays.asList(comment1, comment2, comment3);
        // 4. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("특정 닉네임의 댓글 조회")
    void findByNickname() {
        // 1. 입력 데이터 준비
        String nickname = "john";
        // 2. 실제 데이터
        List<Comment> actual = commentRepository.findByNickname(nickname);
        // 3. 예상 데이터
        Comment comment1 = new Comment(1L, new Article(4L, "인생 영화는?", "댓글 달아주세요"), "john", "기생충");
        Comment comment2 = new Comment(6L, new Article(5L, "인생 음식은?", "댓글 달아주세요"), "john", "라면");
        Comment comment3 = new Comment(8L, new Article(6L, "취미는?", "댓글 달아주세요"), "john", "독서");
        List<Comment> expected = Arrays.asList(comment1, comment2, comment3);
        // 4. 비교 및 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("jane 닉네임으로 댓글 조회")
    void findByNickname_jane(){
        String nickname = "jane";
        List<Comment> actual = commentRepository.findByNickname(nickname);
        Comment comment1 = new Comment(2L, new Article(4L, "인생 영화는?", "댓글 달아주세요"), nickname, "명량");
        Comment comment2 = new Comment(4L, new Article(5L, "인생 음식은?", "댓글 달아주세요"), nickname, "삼겹살");
        Comment comment3 = new Comment(9L, new Article(6L, "취미는?", "댓글 달아주세요"), nickname, "야구");
        List<Comment> expected = Arrays.asList(comment1,comment2,comment3);

        assertEquals(expected.toString(), actual.toString());
    }
}