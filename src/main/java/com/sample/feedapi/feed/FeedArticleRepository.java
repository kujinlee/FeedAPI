package com.sample.feedapi.feed;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface FeedArticleRepository extends Repository<Feed, String> {

    void delete(Feed deleted);

    List<Feed> findAll();
    
    Optional<Feed> findOne(String id);

    Feed findById(String id);
    
    Feed findByUserId(String userId);

    Feed save(Feed saved);
}
