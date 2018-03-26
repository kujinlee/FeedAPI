package com.sample.feedapi.feed;

import java.util.List;

interface FeedService {

    Feed create(Feed feed);

    Feed delete(String id);

    List<Feed> findAll();

    Feed findById(String id);
    
    Feed findByFeedId(String feedId);
    
    List<Feed> findByTopicsIn(List<String> topics);

    Feed update(Feed feed);
}
