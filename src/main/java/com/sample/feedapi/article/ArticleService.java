package com.sample.feedapi.article;

import java.util.List;

public interface ArticleService {

    Article create(Article article);

    Article delete(String id);

    List<Article> findAll();

    Article findById(String id);
    
    Article findByArticleId(String articleId);

    List<Article> findByFeedIdsIn(List<String> feedIds);

    Article update(Article article);
}
