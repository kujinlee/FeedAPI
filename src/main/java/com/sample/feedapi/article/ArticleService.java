package com.sample.feedapi.article;

import java.util.List;

interface ArticleService {

    Article create(Article article);

    Article delete(String id);

    List<Article> findAll();

    Article findById(String id);
    
    Article findByArticleId(String articleId);

    Article update(Article article);
}
