package com.sample.feedapi.feed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

final class Feed {

    @Id
    private String id;

    private String description;

    private String feedId;
    
    private List<String> topics;

    public Feed() {
    	topics = new ArrayList<String>();
    }
    
    public Feed withFeedId(String feedId) {
    	this.feedId = feedId;
    	return this;
    }
    
    public Feed withDescription(String description) {
    	this.description = description;
    	return this;
    }
    
    public Feed withTopics(List<String> topics) {
    	this.topics = topics;
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
    
    public List<String> getTopics() {
        return topics;
    }

    public void update(String feedId, List<String> topics, String description) {
        this.feedId = feedId;
        this.topics = topics;
        this.description = description;
    }

    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	if (topics != null) {
	    	for (String topic: topics) {
	    		sb.append(topic + " ");
	    	}
    	}
        return String.format(
                "User[id=%s, feedId=%s, topics=[%s], description=%s]",
                this.id,
                this.feedId,
                sb.toString(),
                this.description
        );
    }

}
