package com.sample.feedapi.feedarticle;

import java.util.List;

public interface FeedArticleService {

    FeedArticle create(FeedArticle feedArticle);

    FeedArticle delete(String id);

    List<FeedArticle> findAll();

    FeedArticle findById(String id);
    
    List<FeedArticle> findByFeedId(String feedId);

    FeedArticle update(FeedArticle feedArticle);
}
