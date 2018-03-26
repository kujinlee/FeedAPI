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

import java.util.Arrays;
import java.util.List;

@RestController
final class FeedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);

    private final FeedService service;

    @Autowired
    FeedController(FeedService service) {
        this.service = service;
    }

    /**
     * create feed
     * @param feedEntry
     * @return
     */
    @RequestMapping(value = "/feedapi/feed", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Feed create(@RequestBody @Valid Feed feedEntry) {
        LOGGER.info("Creating a new feed entry with information: {}", feedEntry);

        Feed created = service.create(feedEntry);
        LOGGER.info("Created a new feed entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "/feedapi/feed/{id}", method = RequestMethod.DELETE)
    Feed delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting feed entry with id: {}", id);

        Feed deleted = service.delete(id);
        LOGGER.info("Deleted feed entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(value = "/feedapi/feed", method = RequestMethod.GET)
    List<Feed> findAll() {
        LOGGER.info("Finding all feed entries");

        List<Feed> feedEntries = service.findAll();
        LOGGER.info("Found {} feed entries", feedEntries.size());

        return feedEntries;
    }

    @RequestMapping(value = "/feedapi/feed/{id}", method = RequestMethod.GET)
    Feed findById(@PathVariable("id") String id) {
        LOGGER.info("Finding feed entry with id: {}", id);

        Feed feedEntry = service.findById(id);
        LOGGER.info("Found feed entry with information: {}", feedEntry);

        return feedEntry;
    }
    
    @RequestMapping(value = "/feedapi/feed/feedId/{feedId}", method = RequestMethod.GET)
    Feed findByFeedId(@PathVariable("feedId") String feedId) {
        LOGGER.info("Finding feed entry with feedId: {}", feedId);

        Feed feedEntry = service.findByFeedId(feedId);
        LOGGER.info("Found feed entry with information: {}", feedEntry);

        return feedEntry;
    }
    
 // TODO: topic1,topic2 doesn't work. topic1 works
    @RequestMapping(value = "/feedapi/feed/topics/{topicsCsv}", method = RequestMethod.GET)
    List<Feed> findByTopicsIn(@PathVariable("topicsCsv") String topicsCsv) {
        LOGGER.info("Finding feed entries with topics: {}", topicsCsv);
        List<String> topicsToQuery = Arrays.asList(topicsCsv);
        LOGGER.info("Finding feed entry with topics: {}", topicsToQuery);
        List<Feed> feedEntries = service.findByTopicsIn(topicsToQuery);
        LOGGER.info("Found feed entries with information: {}", feedEntries);

        return feedEntries;
    }

    @RequestMapping(value = "/feedapi/feed/{id}", method = RequestMethod.PUT)
    Feed update(@RequestBody @Valid Feed feedEntry, @PathVariable("id") String id) {
        LOGGER.info("Updating feed entry with information: {}", feedEntry);

        Feed updated = service.update(feedEntry);
        LOGGER.info("Updated feed entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleFeedNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
