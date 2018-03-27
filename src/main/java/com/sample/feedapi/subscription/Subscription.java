package com.sample.feedapi.subscription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * subscription to article feeds by user
 * @author klee
 *
 */
final class Subscription {

    @Id
    private String id;

    private String userId;
    
    private List<String> feedIds;

    public Subscription() {
    	feedIds = new ArrayList<String>();
    }
    
    public Subscription withUserId(String userId) {
    	this.userId = userId;
    	return this;
    }
    
    public Subscription withFeedIds(List<String> feedIds) {
    	this.feedIds = feedIds;
    	return this;
    }

    public String getId() {
        return id;
    }

    public List<String> getFeedIds() {
        return feedIds;
    }

    public String getUserId() {
        return userId;
    }

    public void update(String userId, List<String> feedIds) {

        this.userId = userId;
        this.feedIds = feedIds;
    }

    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	if (feedIds != null) {
	    	for (String feedId: feedIds) {
	    		sb.append(feedId + " ");
	    	}
    	}
        return String.format(
                "User[id=%s, userId=%s, feedIds=[%s]]",
                this.id,
                this.userId,
                sb.toString()
        );
    }

}
