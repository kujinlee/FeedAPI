package com.sample.feedapi.feedarticle;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.feedapi.exception.NotFoundException;

@Service
final class FeedArticleServiceMongoDbImpl implements FeedArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedArticleServiceMongoDbImpl.class);

    private final FeedArticleRepository repository;

    @Autowired
    FeedArticleServiceMongoDbImpl(FeedArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public FeedArticle create(FeedArticle feedArticle) {
        LOGGER.info("Creating a new feedArticle entry with information: {}", feedArticle);

        FeedArticle persisted = (new FeedArticle())
                .withFeedId(feedArticle.getFeedId())
                .withArticleId(feedArticle.getArticleId());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new feedArticle entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public FeedArticle delete(String id) {
        LOGGER.info("Deleting a feedArticle entry with id: {}", id);

        FeedArticle deleted = findById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted feedArticle entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<FeedArticle> findAll() {
        LOGGER.info("Finding all feedArticle entries.");

        List<FeedArticle> feedEntries = repository.findAll();

        LOGGER.info("Found {} feedArticle entries", feedEntries.size());

        return feedEntries;
    }

    @Override
    public FeedArticle findById(String id) {
        LOGGER.info("Finding feedArticle entry with id: {}", id);

        //User found = findUserById(id);
        FeedArticle found = repository.findById(id);

        LOGGER.info("Found feedArticle entry: {}", found);

        return found;
    }
    
    @Override
    public List<FeedArticle> findByFeedId(String feedId) {
        LOGGER.info("Finding feedArticle entry with feedId: {}", feedId);

        List<FeedArticle> found = repository.findByFeedId(feedId);

        LOGGER.info("Found feedArticle entry: {}", found);

        return found;
    }

    @Override
    public FeedArticle update(FeedArticle feedArticle) {
        LOGGER.info("Updating feedArticle entry with information: {}", feedArticle);

        FeedArticle updated = repository.findById(feedArticle.getId());
        updated.update(feedArticle.getFeedId(), feedArticle.getArticleId());
        updated = repository.save(updated);

        LOGGER.info("Updated feedArticle entry with information: {}", updated);

        return updated;
    }
}
