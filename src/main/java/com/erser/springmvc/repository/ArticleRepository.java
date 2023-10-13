package com.erser.springmvc.repository;

import com.erser.springmvc.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // ArrayList로 타입 오버라이딩
    @Override
    ArrayList<Article> findAll();
}
