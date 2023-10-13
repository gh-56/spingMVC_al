package com.erser.springmvc;

import com.erser.springmvc.entity.Article;
import com.erser.springmvc.entity.Member;
import com.erser.springmvc.repository.ArticleRepository;
import com.erser.springmvc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer  implements CommandLineRunner {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void run(String... args) throws Exception {
        // 임시 mock data
        articleRepository.save(new Article(1L, "제목1", "내용1"));
        articleRepository.save(new Article(2L, "제목2", "내용2"));
        articleRepository.save(new Article(3L, "제목3", "내용3"));
        memberRepository.save(new Member(1L, "aa@aa.com", "aaaa"));
        memberRepository.save(new Member(2L, "bb@bb.com", "bbbb"));
        memberRepository.save(new Member(3L, "cc@cc.com", "cccc"));
    }
}
