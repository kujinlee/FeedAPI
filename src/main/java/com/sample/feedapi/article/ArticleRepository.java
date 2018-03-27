package com.sample.feedapi.article;

import java.util.List;

import org.springframework.data.repository.Repository;

interface ArticleRepository extends Repository<Article, String> {

    void delete(Article deleted);

    List<Article> findAll();
    
    Article findById(String id);
    
    Article findByArticleId(String articleId);    

    List<Article> findByFeedIdsIn(List<String> feedIdsToQuery);

    Article save(Article saved);
}
