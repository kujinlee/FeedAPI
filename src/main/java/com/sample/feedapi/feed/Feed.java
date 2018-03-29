package com.sample.feedapi.feed;

import org.springframework.data.annotation.Id;

final public class Feed {

    @Id
    private String id;

    private String description;

    private String feedId;
   
    public Feed() {
    }
    
    public Feed withFeedId(String feedId) {
    	this.feedId = feedId;
    	return this;
    }
    
    public Feed withDescription(String description) {
    	this.description = description;
    	return this;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getFeedId() {
        return feedId;
    }

    public void update(String feedId, String description) {
        this.feedId = feedId;
        this.description = description;
    }

    @Override
    public String toString() {

        return String.format(
                "User[id=%s, feedId=%s, description=%s]",
                this.id,
                this.feedId,
                this.description
        );
    }

}
