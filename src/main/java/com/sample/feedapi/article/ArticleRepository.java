package com.sample.feedapi.article;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface ArticleRepository extends Repository<Article, String> {

    void delete(Article deleted);

    List<Article> findAll();
    
    Optional<Article> findOne(String id);

    Article findById(String id);
    
    Article findByUserId(String userId);

    Article save(Article saved);
}
