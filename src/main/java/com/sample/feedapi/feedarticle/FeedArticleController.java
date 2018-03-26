package com.sample.feedapi.feedarticle;

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
final class FeedArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedArticleController.class);

    private final FeedArticleService service;

    @Autowired
    FeedArticleController(FeedArticleService service) {
        this.service = service;
    }

    /**
     * create feed
     * @param userEntry
     * @return
     */
    @RequestMapping(value = "/feedapi/feedarticle", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    FeedArticle create(@RequestBody @Valid FeedArticle feedArticleEntry) {
        LOGGER.info("Creating a new feedArticle entry with information: {}", feedArticleEntry);

        FeedArticle created = service.create(feedArticleEntry);
        LOGGER.info("Created a new feedArticle entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "/feedapi/feedarticle/{id}", method = RequestMethod.DELETE)
    FeedArticle delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting feedArticle entry with id: {}", id);

        FeedArticle deleted = service.delete(id);
        LOGGER.info("Deleted feedArticle entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(value = "/feedapi/feedarticle", method = RequestMethod.GET)
    List<FeedArticle> findAll() {
        LOGGER.info("Finding all feedArticle entries");

        List<FeedArticle> feedArticleEntry = service.findAll();
        LOGGER.info("Found {} feedArticle entries", feedArticleEntry.size());

        return feedArticleEntry;
    }

    @RequestMapping(value = "/feedapi/feedarticle/{id}", method = RequestMethod.GET)
    FeedArticle findById(@PathVariable("id") String id) {
        LOGGER.info("Finding feedArticle entry with id: {}", id);

        FeedArticle feedEntry = service.findById(id);
        LOGGER.info("Found feedArticle entry with information: {}", feedEntry);

        return feedEntry;
    }
    
    @RequestMapping(value = "/feedapi/feedarticle/feedId/{feedId}", method = RequestMethod.GET)
    List<FeedArticle> findByFeedId(@PathVariable("feedId") String feedId) {
        LOGGER.info("Finding feedArticle entry with feedId: {}", feedId);

        List<FeedArticle> feedArticleEntry = service.findByFeedId(feedId);
        LOGGER.info("Found feedArticle entry with information: {}", feedArticleEntry);

        return feedArticleEntry;
    }

    @RequestMapping(value = "/feedapi/feedarticle/{id}", method = RequestMethod.PUT)
    FeedArticle update(@RequestBody @Valid FeedArticle feedArticleEntry, @PathVariable("id") String id) {
        LOGGER.info("Updating feedArticle entry with information: {}", feedArticleEntry);

        FeedArticle updated = service.update(feedArticleEntry);
        LOGGER.info("Updated feedArticle entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleFeedArticleNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
