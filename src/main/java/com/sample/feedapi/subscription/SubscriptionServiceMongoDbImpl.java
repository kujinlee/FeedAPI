package com.sample.feedapi.subscription;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class SubscriptionServiceMongoDbImpl implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceMongoDbImpl.class);

    private final SubscriptionRepository repository;

    @Autowired
    SubscriptionServiceMongoDbImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription create(Subscription subscription) {
        LOGGER.info("Creating a new subscription entry with information: {}", subscription);

        Subscription persisted = (new Subscription())
                .withUserId(subscription.getUserId())
                .withTopics(subscription.getTopics());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new subscription entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public Subscription delete(String id) {
        LOGGER.info("Deleting a subscription entry with id: {}", id);

        Subscription deleted = findSubscriptionById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted subscription entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<Subscription> findAll() {
        LOGGER.info("Finding all subscription entries.");

        List<Subscription> subscriptionEntries = repository.findAll();

        LOGGER.info("Found {} subscription entries", subscriptionEntries.size());

        return subscriptionEntries;
    }

    @Override
    public Subscription findById(String id) {
        LOGGER.info("Finding subscription entry with id: {}", id);

        Subscription found = findSubscriptionById(id);

        LOGGER.info("Found todo entry: {}", found);

        return found;
    }

    @Override
    public Subscription update(Subscription subscription) {
        LOGGER.info("Updating todo entry with information: {}", subscription);

        Subscription updated = findSubscriptionById(subscription.getId());
        updated.update(subscription.getUserId(), subscription.getTopics());
        updated = repository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return updated;
    }

    private Subscription findSubscriptionById(String id) {
//        Optional<Subscription> result = repository.findOne(id);
//        return result.orElseThrow(() -> new NotFoundException(id));
      Subscription result = repository.findById(id);
      return result;    	
    }

	@Override
	public Subscription findByUserId(String userId) {
		Subscription result = repository.findByUserId(userId);
        return result;
	}

	@Override
	public List<Subscription> findByTopicsIn(List<String> topicsToQuery) {
		List<Subscription> result = repository.findByTopicsIn(topicsToQuery);
        return result;
	}

}
