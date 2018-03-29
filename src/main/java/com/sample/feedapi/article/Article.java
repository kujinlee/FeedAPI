package com.sample.feedapi.article;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

final public class Article {

    @Id
    private String id;

    private String content;

    private String articleId;
    
    private List<String> feedIds;

    public Article() {
    	feedIds = new ArrayList<String>();
    }
    
    public Article withArticleId(String articleId) {
    	this.articleId = articleId;
    	return this;
    }
    
    public Article withContent(String content) {
    	this.content = content;
    	return this;
    }
    
    public Article withFeedIds(List<String> feedIds) {
    	this.feedIds = feedIds;
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
    
    public List<String> getFeedIds() {
        return feedIds;
    }

    public void update(String articleId, List<String> feedIds, String content) {

        this.articleId = articleId;
        this.feedIds = feedIds;
        this.content = content;
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
                "User[id=%s, articleId=%s, feedIds=[%s], content=%s]",
                this.id,
                this.articleId,
                sb.toString(),
                this.content
        );
    }

}
