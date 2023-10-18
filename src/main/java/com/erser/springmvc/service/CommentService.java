package com.erser.springmvc.service;

import com.erser.springmvc.dto.CommentDto;
import com.erser.springmvc.entity.Article;
import com.erser.springmvc.entity.Comment;
import com.erser.springmvc.repository.ArticleRepository;
import com.erser.springmvc.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
//        // 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 엔티티 -> DTO 변환
//        List<CommentDto> commentDtos = new ArrayList();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment comment = comments.get(i);
//            CommentDto commentDto = CommentDto.createCommentDto(comment);
//            commentDtos.add(commentDto);
//        }
        // 결과 반환
        return commentRepository.findByArticleId(articleId).stream().map(CommentDto::createCommentDto).toList();
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. 댓글 생성 실패."));
        Comment comment = Comment.createComment(dto, article);
        Comment created = commentRepository.save(comment);
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB 갱신
        Comment updated = commentRepository.save(target);
        // 4. DTO 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 예외 처리
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패"));
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. DTO로 리턴
        return CommentDto.createCommentDto(target);
    }
}
