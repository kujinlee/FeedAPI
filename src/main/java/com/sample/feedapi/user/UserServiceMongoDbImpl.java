package com.sample.feedapi.user;

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

    private final UserRepository repository;

    @Autowired
    UserServiceMongoDbImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        LOGGER.info("Creating a new user entry with information: {}", user);

        User persisted = (new User())
                .withUserId(user.getUserId())
                .withName(user.getName());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new user entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public User delete(String id) {
        LOGGER.info("Deleting a user entry with id: {}", id);

        User deleted = findUserById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted user entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<User> todoEntries = repository.findAll();

        LOGGER.info("Found {} user entries", todoEntries.size());

        return todoEntries;
    }

    @Override
    public User findById(String id) {
        LOGGER.info("Finding user entry with id: {}", id);

        //User found = findUserById(id);
        User found = repository.findById(id);

        LOGGER.info("Found user entry: {}", found);

        return found;
    }
    
    @Override
    public User findByUserId(String userId) {
        LOGGER.info("Finding user entry with userId: {}", userId);

        //User found = findUserByUserId(userId);
        User found = repository.findByUserId(userId);

        LOGGER.info("Found user entry: {}", found);

        return found;
    }

    @Override
    public User update(User user) {
        LOGGER.info("Updating user entry with information: {}", user);

        User updated = findUserById(user.getId());
        updated.update(user.getName(), user.getUserId());
        updated = repository.save(updated);

        LOGGER.info("Updated user entry with information: {}", updated);

        return updated;
    }

    private User findUserById(String id) {
//    	 Optional<User> result = repository.findOne(id);
//         return result.orElseThrow(() -> new NotFoundException(id));
        User result = repository.findById(id);
        return result;
    }
    
    private User findUserByUserId(String userId) {
        User result = repository.findByUserId(userId);
        return result;
    }

}
