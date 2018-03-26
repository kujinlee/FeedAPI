package com.sample.feedapi.feedarticle;

import org.springframework.data.annotation.Id;

/**
 * Feed and Article pair
 * Collection of FeedArticle contains all articles belongs to the feed
 * Same article can belongs to multiple feeds
 * Collection of FeedArticle will be saved in mongodb
 * 
 * FeedArticle instance is created when an Article is fed 
 * and inserted into mongodb 
 * @author klee
 *
 */
final class FeedArticle {

    @Id
    private String id;

    private String feedId;
    
    private String articleId;

    public FeedArticle() {}
    
    public FeedArticle withFeedId(String feedId) {
    	this.feedId = feedId;
    	return this;
    }
    
    public FeedArticle withArticleId(String articleId) {
    	this.articleId = articleId;
    	return this;
    }

    public String getId() {
        return id;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void update(String feedId, String articleId) {
        this.feedId = feedId;
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, feedId=%s, articleId=%s]",
                this.id,
                this.feedId,
                this.articleId
        );
    }

}
