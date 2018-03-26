package com.sample.feedapi.feed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.feedapi.exception.NotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
final class FeedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

    private final UserService service;

    @Autowired
    FeedController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/feedapi/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Feed create(@RequestBody @Valid Feed userEntry) {
        LOGGER.info("Creating a new user entry with information: {}", userEntry);

        Feed created = service.create(userEntry);
        LOGGER.info("Created a new user entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "/feedapi/user/{id}", method = RequestMethod.DELETE)
    Feed delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting user entry with id: {}", id);

        Feed deleted = service.delete(id);
        LOGGER.info("Deleted user entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(value = "/feedapi/user", method = RequestMethod.GET)
    List<Feed> findAll() {
        LOGGER.info("Finding all user entries");

        List<Feed> userEntries = service.findAll();
        LOGGER.info("Found {} user entries", userEntries.size());

        return userEntries;
    }

    @RequestMapping(value = "/feedapi/user/{id}", method = RequestMethod.GET)
    Feed findById(@PathVariable("id") String id) {
        LOGGER.info("Finding user entry with id: {}", id);

        Feed userEntry = service.findById(id);
        LOGGER.info("Found user entry with information: {}", userEntry);

        return userEntry;
    }
    
    @RequestMapping(value = "/feedapi/user/userId/{userId}", method = RequestMethod.GET)
    Feed findByUserId(@PathVariable("userId") String userId) {
        LOGGER.info("Finding user entry with id: {}", userId);

        Feed userEntry = service.findByUserId(userId);
        LOGGER.info("Found user entry with information: {}", userEntry);

        return userEntry;
    }

    @RequestMapping(value = "/feedapi/user/{id}", method = RequestMethod.PUT)
    Feed update(@RequestBody @Valid Feed userEntry, @PathVariable("id") String id) {
        LOGGER.info("Updating user entry with information: {}", userEntry);

        Feed updated = service.update(userEntry);
        LOGGER.info("Updated user entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleUserNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
