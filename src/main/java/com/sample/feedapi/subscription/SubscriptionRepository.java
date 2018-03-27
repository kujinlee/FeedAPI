package com.sample.feedapi.subscription;

import java.util.List;

import org.springframework.data.repository.Repository;

interface SubscriptionRepository extends Repository<Subscription, String> {

    void delete(Subscription deleted);

    List<Subscription> findAll();

    Subscription findById(String id);
    
    Subscription findByUserId(String userId);
    
    List<Subscription> findByFeedIdsIn(List<String> feedIdsToQuery);

    Subscription save(Subscription saved);
}
