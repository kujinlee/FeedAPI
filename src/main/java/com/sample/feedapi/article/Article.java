package com.sample.feedapi.article;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

final class Article {

    @Id
    private String id;

    private String content;

    private String articleId;
    
    private List<String> topics;

    public Article() {
    	topics = new ArrayList<String>();
    }
    
    public Article withArticleId(String articleId) {
    	this.articleId = articleId;
    	return this;
    }
    
    public Article withContent(String content) {
    	this.content = content;
    	return this;
    }
    
    public Article withTopics(List<String> topics) {
    	this.topics = topics;
    	return this;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getArticleId() {
        return articleId;
    }
    
    public List<String> getTopics() {
        return topics;
    }

    public void update(String articleId, List<String> topics, String content) {

        this.articleId = articleId;
        this.topics = topics;
        this.content = content;
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
                "User[id=%s, articleId=%s, topics=[%s], content=%s]",
                this.id,
                this.articleId,
                sb.toString(),
                this.content
        );
    }

}
