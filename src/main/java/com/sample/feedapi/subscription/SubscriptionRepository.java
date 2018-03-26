package com.sample.feedapi.subscription;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SubscriptionRepository extends Repository<Subscription, String> {

    void delete(Subscription deleted);

    List<Subscription> findAll();

    Optional<Subscription> findOne(String id);
    
    Subscription findById(String id);
    
    Subscription findByUserId(String userId);
    
    List<Subscription> findByTopicsIn(List<String> topicsToQuery);

    Subscription save(Subscription saved);
}
