package com.erser.springmvc.api;

import com.erser.springmvc.dto.ArticleForm;
import com.erser.springmvc.entity.Article;
import com.erser.springmvc.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleApiController {

    // DI
    private final ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 1. dto -> entity
        Article article = dto.toEntity();
//        log.info("article =" + article.toString());
        // 2. 타겟 조회하기
        Article target = articleRepository.findById(id).orElse(null);
//        log.info("target =" + target.toString());
        // 3. 잘못된 요청 처리
//        log.info("id = " + id);
        if(target == null || id != target.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200) 하기
        target.patch(article);
        Article updated = articleRepository.save(target);
//        log.info("updated =" + updated.toString());
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);

        if(article == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(article);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
