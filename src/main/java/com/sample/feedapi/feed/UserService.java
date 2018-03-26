package com.sample.feedapi.feed;

import java.util.List;

interface UserService {

    Feed create(Feed user);

    Feed delete(String id);

    List<Feed> findAll();

    Feed findById(String id);
    
    Feed findByUserId(String userId);

    Feed update(Feed user);
}
