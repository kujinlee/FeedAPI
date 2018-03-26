package com.sample.feedapi.feedarticle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface FeedArticleRepository extends Repository<FeedArticle, String> {

    void delete(FeedArticle deleted);

    List<FeedArticle> findAll();
    
    Optional<FeedArticle> findOne(String id);

    FeedArticle findById(String id);
    
    List<FeedArticle> findByFeedId(String feedId);

    FeedArticle save(FeedArticle saved);
}
