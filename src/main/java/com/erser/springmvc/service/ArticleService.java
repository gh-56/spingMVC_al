package com.erser.springmvc.service;

import com.erser.springmvc.dto.ArticleForm;
import com.erser.springmvc.entity.Article;
import com.erser.springmvc.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }
    public List<Article> index(){
        return articleRepository.findAll();
    }
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }
    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    public Article update(Long id, ArticleForm dto){
        // 1. dto -> entity
        Article article = dto.toEntity();
//        log.info("article =" + article.toString());
        // 2. 타겟 조회하기
        Article target = articleRepository.findById(id).orElse(null);
//        log.info("target =" + target.toString());
        // 3. 잘못된 요청 처리
//        log.info("id = " + id);
        if(target == null || id != target.getId()){
            return null;
        }
        // 4. 업데이트 및 정상 응답(200) 하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id){
        Article article = articleRepository.findById(id).orElse(null);

        if(article == null){
            return null;
        }
        articleRepository.delete(article);
        return article;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        // 1. dto 목록을 엔티티 목록으로 변환하기
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).toList();
        articleList.forEach(articleRepository::save);
        return articleList;
    }
}
