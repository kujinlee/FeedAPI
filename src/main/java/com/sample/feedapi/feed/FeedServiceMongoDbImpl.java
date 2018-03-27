package com.sample.feedapi.feed;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class FeedServiceMongoDbImpl implements FeedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedServiceMongoDbImpl.class);

    private final FeedRepository repository;

    @Autowired
    FeedServiceMongoDbImpl(FeedRepository repository) {
        this.repository = repository;
    }

    @Override
    public Feed create(Feed feed) {
        LOGGER.info("Creating a new feed entry with information: {}", feed);

        Feed persisted = (new Feed())
                .withFeedId(feed.getFeedId())
                .withDescription(feed.getDescription());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new feed entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public Feed delete(String id) {
        LOGGER.info("Deleting a feed entry with id: {}", id);

        Feed deleted = findById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted feed entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<Feed> findAll() {
        LOGGER.info("Finding all feed entries.");

        List<Feed> feedEntries = repository.findAll();

        LOGGER.info("Found {} feed entries", feedEntries.size());

        return feedEntries;
    }

    @Override
    public Feed findById(String id) {
        LOGGER.info("Finding feed entry with id: {}", id);

        //User found = findUserById(id);
        Feed found = repository.findById(id);

        LOGGER.info("Found feed entry: {}", found);

        return found;
    }
    
    @Override
    public Feed findByFeedId(String feedId) {
        LOGGER.info("Finding feed entry with feedId: {}", feedId);

        Feed found = repository.findByFeedId(feedId);

        LOGGER.info("Found feed entry: {}", found);

        return found;
    }

    @Override
    public Feed update(Feed feed) {
        LOGGER.info("Updating feed entry with information: {}", feed);

        Feed updated = repository.findById(feed.getId());
        updated.update(feed.getFeedId(), feed.getDescription());
        updated = repository.save(updated);

        LOGGER.info("Updated feed entry with information: {}", updated);

        return updated;
    }
}
