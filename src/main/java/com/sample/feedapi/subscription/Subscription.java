package com.sample.feedapi.subscription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * subscription to article topics by user
 * @author klee
 *
 */
final class Subscription {

    @Id
    private String id;

    private String userId;
    
    private List<String> topics;

    public Subscription() {
    	topics = new ArrayList<String>();
    }
    
    public Subscription withUserId(String userId) {
    	this.userId = userId;
    	return this;
    }
    
    public Subscription withTopics(List<String> topics) {
    	this.topics = topics;
    	return this;
    }

    public String getId() {
        return id;
    }

    public List<String> getTopics() {
        return topics;
    }

    public String getUserId() {
        return userId;
    }

    public void update(String userId, List<String> topics) {

        this.userId = userId;
        this.topics = topics;
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
                "User[id=%s, userId=%s, topics=[%s]]",
                this.id,
                this.userId,
                sb.toString()
        );
    }

}
