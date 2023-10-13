package com.erser.springmvc;

import com.erser.springmvc.entity.Article;
import com.erser.springmvc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}
//	public void initData(){
//		// 임시 mock data
//		articleRepository.save(new Article(1L, "제목1", "내용1"));
//		articleRepository.save(new Article(2L, "제목2", "내용2"));
//		articleRepository.save(new Article(3L, "제목3", "내용3"));
//	}
}
