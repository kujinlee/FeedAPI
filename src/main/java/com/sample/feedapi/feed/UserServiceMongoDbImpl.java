package com.sample.feedapi.feed;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.feedapi.exception.NotFoundException;

@Service
final class UserServiceMongoDbImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceMongoDbImpl.class);

    private final FeedArticleRepository repository;

    @Autowired
    UserServiceMongoDbImpl(FeedArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Feed create(Feed user) {
        LOGGER.info("Creating a new user entry with information: {}", user);

        Feed persisted = (new Feed())
                .withFeedId(user.getUserId())
                .withDescription(user.getName());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new user entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public Feed delete(String id) {
        LOGGER.info("Deleting a user entry with id: {}", id);

        Feed deleted = findUserById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted user entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<Feed> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<Feed> todoEntries = repository.findAll();

        LOGGER.info("Found {} user entries", todoEntries.size());

        return todoEntries;
    }

    @Override
    public Feed findById(String id) {
        LOGGER.info("Finding user entry with id: {}", id);

        //User found = findUserById(id);
        Feed found = repository.findById(id);

        LOGGER.info("Found user entry: {}", found);

        return found;
    }
    
    @Override
    public Feed findByUserId(String userId) {
        LOGGER.info("Finding user entry with userId: {}", userId);

        //User found = findUserByUserId(userId);
        Feed found = repository.findByUserId(userId);

        LOGGER.info("Found user entry: {}", found);

        return found;
    }

    @Override
    public Feed update(Feed user) {
        LOGGER.info("Updating user entry with information: {}", user);

        Feed updated = findUserById(user.getId());
        updated.update(user.getName(), user.getUserId());
        updated = repository.save(updated);

        LOGGER.info("Updated user entry with information: {}", updated);

        return updated;
    }

    private Feed findUserById(String id) {
//    	 Optional<User> result = repository.findOne(id);
//         return result.orElseThrow(() -> new NotFoundException(id));
        Feed result = repository.findById(id);
        return result;
    }
    
    private Feed findUserByUserId(String userId) {
        Feed result = repository.findByUserId(userId);
        return result;
    }

}
