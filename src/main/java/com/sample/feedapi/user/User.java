package com.sample.feedapi.user;

import org.springframework.data.annotation.Id;

final class User {

    @Id
    private String id;

    private String name;

    private String userId;

    public User() {}
    
    public User withUserId(String userId) {
    	this.userId = userId;
    	return this;
    }
    
    public User withName(String name) {
    	this.name = name;
    	return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void update(String name, String userId) {

        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name=%s, userId=%s]",
                this.id,
                this.name,
                this.userId
        );
    }

}
