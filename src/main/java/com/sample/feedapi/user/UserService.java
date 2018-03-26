package com.sample.feedapi.user;

import java.util.List;

interface UserService {

    User create(User user);

    User delete(String id);

    List<User> findAll();

    User findById(String id);
    
    User findByUserId(String userId);

    User update(User user);
}
