package com.erser.springmvc;

import com.erser.springmvc.entity.*;
import com.erser.springmvc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer  implements CommandLineRunner {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Override
    public void run(String... args) throws Exception {
        // 임시 mock data
        articleRepository.save(new Article(1L, "제목1", "내용1"));
        articleRepository.save(new Article(2L, "제목2", "내용2"));
        articleRepository.save(new Article(3L, "제목3", "내용3"));
        memberRepository.save(new Member(1L, "aa@aa.com", "aaaa"));
        memberRepository.save(new Member(2L, "bb@bb.com", "bbbb"));
        memberRepository.save(new Member(3L, "cc@cc.com", "cccc"));
        coffeeRepository.save(new Coffee(1L, "아메리카노", "4500"));
        coffeeRepository.save(new Coffee(2L, "라떼", "5000"));
        coffeeRepository.save(new Coffee(3L, "카페모카", "5500"));
        // 댓글 임시 mock data
        Article article4 = new Article(4L, "인생 영화는?", "댓글 달아주세요");
        Article article5 = new Article(5L, "인생 음식은?", "댓글 달아주세요");
        Article article6 = new Article(6L, "취미는?", "댓글 달아주세요");

        articleRepository.save(article4);
        articleRepository.save(article5);
        articleRepository.save(article6);
        commentRepository.save(new Comment(1L, article4, "john", "기생충"));
        commentRepository.save(new Comment(2L, article4, "jane", "명량"));
        commentRepository.save(new Comment(3L, article4, "tom", "신과함께"));
        commentRepository.save(new Comment(4L, article5, "jane", "삼겹살"));
        commentRepository.save(new Comment(5L, article5, "tom", "치킨"));
        commentRepository.save(new Comment(6L, article5, "john", "라면"));
        commentRepository.save(new Comment(7L, article6, "tom", "영화"));
        commentRepository.save(new Comment(8L, article6, "john", "독서"));
        commentRepository.save(new Comment(9L, article6, "jane", "야구"));

        pizzaRepository.save(new PizzaEntity(1L, "페퍼로니 피자", "25,900"));
        pizzaRepository.save(new PizzaEntity(2L, "불고기 피자", "29,900"));
        pizzaRepository.save(new PizzaEntity(3L, "고구마 피자", "30,900"));
        pizzaRepository.save(new PizzaEntity(4L, "포테이토 피자", "27,900"));
    }
}
