package com.sample.feedapi.feed;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface FeedRepository extends Repository<Feed, String> {

    void delete(Feed deleted);

    List<Feed> findAll();
    
    Optional<Feed> findOne(String id);

    Feed findById(String id);
    
    Feed findByFeedId(String feedId);

    Feed save(Feed saved);
}
