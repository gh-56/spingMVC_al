package com.erser.springmvc.service;

import com.erser.springmvc.dto.ArticleForm;
import com.erser.springmvc.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트와 연동해서 테스트 하겠다.
class ArticleServiceTest {

    @Autowired // 의존성 주입
    ArticleService articleService;

    @Test
    void index() {
        // TDD 테스트 주도 개발
            // 테스트를 먼저 하고 통과하면 코드를 개발하는 기법
        // Given - When - Then 기법
        // 1. 예상되는 데이터 => expected
        Article a = new Article(1L, "제목1", "내용1");
        Article b = new Article(2L, "제목2", "내용2");
        Article c = new Article(3L, "제목3", "내용3");
        ArrayList<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        // 2. 실제 데이터 => actual
        List<Article> articleList = articleService.index();
        // 3. 비교와 검증
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        // 1. expected 예상
        Article expected = new Article(id, "제목1", "내용1");
        // 2. actual 실제
        Article actual = articleService.show(id);
        // 3. assertion 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_실패_존재하지않는ID입력() {
        Long id = -1L;
        Article expected = null;
        Article actual = articleService.show(id);
        assertEquals(expected , actual );
    }
    @Test
    @Transactional // 테스트가 종료한 뒤 변경된 데이터를 rollback 한다.
    void create_성공() {
        String title = "제목4";
        String content = "내용4";
        // expected
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(4L, title, content);
        // actual
        Article actual = articleService.create(dto);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void create_실패() {

    }

    @Test
    @Transactional
    void update() {
        Long id = 1L;
        String title = "수정 타이틀";
        String content = "수정 콘텐츠";

        Article expected = new Article(id, title, content);

        ArticleForm dto = new ArticleForm(title, content);
        Article actual = articleService.update(id, dto);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_타이틀만_수정하는경우() {
        Long id = 1L;
        String title = "수정 타이틀";
        String content = null;

        Article expected = new Article(id, title, "내용1");

        ArticleForm dto = new ArticleForm(title, content);
        Article actual = articleService.update(id, dto);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_콘텐츠만_수정하는경우() {
        Long id = 1L;
        String title = null;
        String content = "수정 콘텐츠";

        Article expected = new Article(id, "제목1", content);

        ArticleForm dto = new ArticleForm(title, content);
        Article actual = articleService.update(id, dto);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지않는ID_수정하는경우() {
        Long id = -1L;
        String title = "수정 타이틀";
        String content = "수정 콘텐츠";

        Article expected = null;

        ArticleForm dto = new ArticleForm(title, content);
        Article actual = articleService.update(id, dto);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete() {
        Long id = 3L;
        Article expected = new Article(id, "제목3", "내용3");
        Article actual = articleService.delete(id);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void delete_null(){
        Long id = -1L;
        Article expected = null;
        Article actual = articleService.delete(id);
        assertEquals(expected, actual);
    }

    @Test
    void createArticles() {
    }
}