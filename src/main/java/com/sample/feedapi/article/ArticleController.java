package com.sample.feedapi.article;

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
final class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService service;

    @Autowired
    ArticleController(ArticleService service) {
        this.service = service;
    }

    @RequestMapping(value = "/feedapi/article", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Article create(@RequestBody @Valid Article articleEntry) {
        LOGGER.info("Creating a new article entry with information: {}", articleEntry);

        Article created = service.create(articleEntry);
        LOGGER.info("Created a new article entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "/feedapi/article/{id}", method = RequestMethod.DELETE)
    Article delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting article entry with id: {}", id);

        Article deleted = service.delete(id);
        LOGGER.info("Deleted article entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(value = "/feedapi/article", method = RequestMethod.GET)
    List<Article> findAll() {
        LOGGER.info("Finding all article entries");

        List<Article> articleEntries = service.findAll();
        LOGGER.info("Found {} article entries", articleEntries.size());

        return articleEntries;
    }

    @RequestMapping(value = "/feedapi/article/{id}", method = RequestMethod.GET)
    Article findById(@PathVariable("id") String id) {
        LOGGER.info("Finding article entry with id: {}", id);

        Article articleEntry = service.findById(id);
        LOGGER.info("Found article entry with information: {}", articleEntry);

        return articleEntry;
    }
    
    @RequestMapping(value = "/feedapi/article/articleId/{articleId}", method = RequestMethod.GET)
    Article findByUserId(@PathVariable("articleId") String articleId) {
        LOGGER.info("Finding article entry with id: {}", articleId);

        Article articleEntry = service.findByArticleId(articleId);
        LOGGER.info("Found article entry with information: {}", articleEntry);

        return articleEntry;
    }

    @RequestMapping(value = "/feedapi/article/{id}", method = RequestMethod.PUT)
    Article update(@RequestBody @Valid Article articleEntry, @PathVariable("id") String id) {
        LOGGER.info("Updating article entry with information: {}", articleEntry);

        Article updated = service.update(articleEntry);
        LOGGER.info("Updated article entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleUserNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
