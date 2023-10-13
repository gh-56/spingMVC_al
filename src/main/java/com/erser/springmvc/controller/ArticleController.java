package com.erser.springmvc.controller;

import com.erser.springmvc.dto.ArticleForm;
import com.erser.springmvc.entity.Article;
import com.erser.springmvc.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j      // 로깅 기능을 위한 어노테이션
@RequiredArgsConstructor    // 생성자를 통한 의존성 주입
public class ArticleController {

    // 스프링부트에서 스프링 컨테이너에 의존성 주입(DI)
    private final ArticleRepository articleRepository;

//    @Autowired // 스프링에서 생성자가 하나만 있을 때, @Autowired가 없어도 자동으로 DI를 한다.
//    public ArticleController(ArticleRepository articleRepository){
//        this.articleRepository = articleRepository;
//    }
    // CREATE
    @GetMapping("/article/new")
    public String newArticleForm() {
        // Logger logger = LoggerFactory.getLogger(ArticleController.class);
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm dto) {
        // 폼 데이터를 DTO로 전달받아 확인
        log.info(dto.toString());
        // 1. DTO를 도메인 Entity로 변환
        Article article = dto.toEntity();
        // 2. Repository (DAO)로 Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        // 리다이렉트를 통해 새로운 주소 요청
        // "redirect:URL 주소
        return "redirect:/article/"+ saved.getId();
    }

    // READ
    @GetMapping("/article/{id}")
    public String show(@PathVariable Long id, Model model){
        // URL 경로 상에 있는 변수를 어노테이션으로 확인
        log.info("id : " + id);
        // 1. id 조회하여 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터를 등록하기
        model.addAttribute("article", article);
        // 3. 뷰 페이지
        return "articles/show";
    }

    // READ ALL
    @GetMapping("/articles")
    public String index(Model model){
        List<Article> all = articleRepository.findAll();
        model.addAttribute("articleList", all);
        return "articles/index";
    }

    // UPDATE
    @GetMapping("/article/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        // View Resolve
        return "articles/edit";
    }

    @PostMapping("/article/{id}/update")
    public String update(ArticleForm dto, @PathVariable Long id){
        log.info(dto.toString());
        // 1. DTO를 엔티티로 변환
        Article article = dto.toEntity();
        // 2. 엔티티를 DB에 저장
        // 2-1 DB에서 기존 데이터를 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2-2 데이터 갱신하기
        if(target != null){
            article.setId(id);
            articleRepository.save(article);
        }
        return "redirect:/article/"+id;
    }
}
