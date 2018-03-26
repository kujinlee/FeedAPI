package com.sample.feedapi.subscription;

import java.util.List;

interface SubscriptionService {

    Subscription create(Subscription subscription);

    Subscription delete(String id);

    List<Subscription> findAll();

    Subscription findById(String id);
    
    Subscription findByUserId(String userId);
    
    List<Subscription> findByTopicsIn(List<String> topics);

    Subscription update(Subscription subscription);
}
