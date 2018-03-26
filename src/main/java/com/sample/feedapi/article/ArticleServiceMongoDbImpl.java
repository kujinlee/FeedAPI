package com.sample.feedapi.article;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.feedapi.exception.NotFoundException;

@Service
final class ArticleServiceMongoDbImpl implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceMongoDbImpl.class);

    private final ArticleRepository repository;

    @Autowired
    ArticleServiceMongoDbImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Article create(Article article) {
        LOGGER.info("Creating a new article entry with information: {}", article);

        Article persisted = (new Article())
                .withArticleId(article.getArticleId())
                .withContent(article.getContent());

        persisted = repository.save(persisted);
        LOGGER.info("Created a new article entry with information: {}", persisted);

        return persisted;
    }

    @Override
    public Article delete(String id) {
        LOGGER.info("Deleting a article entry with id: {}", id);

        Article deleted = repository.findById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted article entry with informtation: {}", deleted);

        return deleted;
    }

    @Override
    public List<Article> findAll() {
        LOGGER.info("Finding all article entries.");

        List<Article> articleEntries = repository.findAll();

        LOGGER.info("Found {} article entries", articleEntries.size());

        return articleEntries;
    }

    @Override
    public Article findById(String id) {
        LOGGER.info("Finding article entry with id: {}", id);

        //User found = findUserById(id);
        Article found = repository.findById(id);

        LOGGER.info("Found article entry: {}", found);

        return found;
    }
    
    @Override
    public Article findByArticleId(String articleId) {
        LOGGER.info("Finding article entry with articleId: {}", articleId);

        //User found = findUserByUserId(articleId);
        Article found = repository.findByUserId(articleId);

        LOGGER.info("Found article entry: {}", found);

        return found;
    }

    @Override
    public Article update(Article article) {
        LOGGER.info("Updating article entry with information: {}", article);

        Article updated = repository.findById(article.getId());
        updated.update(article.getArticleId(), article.getTopics(), article.getContent());
        updated = repository.save(updated);

        LOGGER.info("Updated article entry with information: {}", updated);

        return updated;
    }

}
