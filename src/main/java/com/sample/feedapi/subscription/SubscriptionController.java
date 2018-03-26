package com.sample.feedapi.subscription;

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

import java.util.Arrays;
import java.util.List;

@RestController
final class SubscriptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService service;

    @Autowired
    SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    /**
     * this API is to subscribe a user to one of more topics
     * 
     * @param subscriptionEntry
     * @return
     */
    @RequestMapping(value = "/feedapi/subscription", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Subscription create(@RequestBody @Valid Subscription subscriptionEntry) {
        LOGGER.info("Creating a new subscription entry with information: {}", subscriptionEntry);

        Subscription created = service.create(subscriptionEntry);
        LOGGER.info("Created a new subscription entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "/feedapi/subscription/{id}", method = RequestMethod.DELETE)
    Subscription delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting subscription entry with id: {}", id);

        Subscription deleted = service.delete(id);
        LOGGER.info("Deleted subscription entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(value = "/feedapi/subscription", method = RequestMethod.GET)
    List<Subscription> findAll() {
        LOGGER.info("Finding all subscription entries");

        List<Subscription> subscriptionEntry = service.findAll();
        LOGGER.info("Found {} subscription entries", subscriptionEntry.size());

        return subscriptionEntry;
    }

    @RequestMapping(value = "/feedapi/subscription/{id}", method = RequestMethod.GET)
    Subscription findById(@PathVariable("id") String id) {
        LOGGER.info("Finding subscription entry with id: {}", id);

        Subscription subscriptionEntry = service.findById(id);
        LOGGER.info("Found subscription entry with information: {}", subscriptionEntry);

        return subscriptionEntry;
    }
    
    @RequestMapping(value = "/feedapi/subscription/userId/{userId}", method = RequestMethod.GET)
    Subscription findByUserId(@PathVariable("userId") String userId) {
        LOGGER.info("Finding subscription entry with userId: {}", userId);

        Subscription subscriptionEntry = service.findByUserId(userId);
        LOGGER.info("Found subscription entry with information: {}", subscriptionEntry);

        return subscriptionEntry;
    }
    
    // TODO: topic1,topic2 doesn't work. topic1 works
    @RequestMapping(value = "/feedapi/subscription/topics/{topicsCsv}", method = RequestMethod.GET)
    List<Subscription> findByTopicsIn(@PathVariable("topicsCsv") String topicsCsv) {
        LOGGER.info("Finding subscription entries with topics: {}", topicsCsv);
        List<String> topicsToQuery = Arrays.asList(topicsCsv);
        LOGGER.info("Finding subscription entries with topics: {}", topicsToQuery);
        List<Subscription> subscriptionEntries = service.findByTopicsIn(topicsToQuery);
        LOGGER.info("Found subscription entries with information: {}", subscriptionEntries);

        return subscriptionEntries;
    }

    @RequestMapping(value = "/feedapi/subscription/{id}", method = RequestMethod.PUT)
    Subscription update(@RequestBody @Valid Subscription userEntry, @PathVariable("id") String id) {
        LOGGER.info("Updating subscription entry with information: {}", userEntry);

        Subscription updated = service.update(userEntry);
        LOGGER.info("Updated subscription entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleSubscriptionNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
