package com.sample.feedapi.subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription create(Subscription subscription);

    Subscription delete(String id);
    
    Subscription deleteByUserId(String userId);
    
    List<Subscription> findAll();

    Subscription findById(String id);
    
    Subscription findByUserId(String userId);
    
    List<Subscription> findByFeedIdsIn(List<String> feedIds);

    Subscription update(Subscription subscription);
}
