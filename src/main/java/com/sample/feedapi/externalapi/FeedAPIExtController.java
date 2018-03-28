package com.sample.feedapi.externalapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

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

import com.sample.feedapi.article.Article;
import com.sample.feedapi.article.ArticleService;
import com.sample.feedapi.exception.NotFoundException;
import com.sample.feedapi.feed.Feed;
import com.sample.feedapi.feed.FeedService;
import com.sample.feedapi.feedarticle.FeedArticle;
import com.sample.feedapi.feedarticle.FeedArticleService;
import com.sample.feedapi.subscription.Subscription;
import com.sample.feedapi.subscription.SubscriptionService;
import com.sample.feedapi.user.User;
import com.sample.feedapi.user.UserService;

@RestController
public class FeedAPIExtController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedAPIExtController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private FeedService feedService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private FeedArticleService feedArticleService;


    FeedAPIExtController() {
    }

    /**
     * Subscribe a user to one of more feeds
     * @param userId
     * @param subscriptionEntry
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/subscription/userId/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Subscription create(
    		@PathVariable("userId") String userId,
    		@RequestBody @Valid Subscription subscriptionEntry) {
        LOGGER.info("Creating a new subscription entry with information: {}", subscriptionEntry);

        // TODO: check if it is a valid user
        // assert subscriptionEntry is for the user
        User user = userService.findByUserId(userId);
        if (user == null) {
        	// if new user, add the user
        	// this could be exception if user has to be created onboarding process
        	user = new User().withUserId(userId).withName("Name for "+userId);
        	userService.create(user);
        }
        
        // create a subscription
        Subscription created = subscriptionService.create(subscriptionEntry);
        LOGGER.info("Created a new subscription entry with information: {}", created);

        return created;
    }
    
    /**
     * Ingest an Article and associate it with designated Feeds
     * @param articleEntry
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/article/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Article create(@RequestBody @Valid Article articleEntry) {
        LOGGER.info("Creating a new article entry with information: {}", articleEntry);

        // create a new article with given articleEntry
        Article createdArticle = articleService.create(articleEntry);
        LOGGER.info("Created a new article entry with information: {}", createdArticle);
        
        // create one or more FeedArticle for the given listOfFeed for the article
        List<String> feeds = createdArticle.getFeedIds();
        String articleId = createdArticle.getArticleId();
        for (String feedId:feeds) {
        	
        	Feed feed = feedService.findByFeedId(feedId);
        	if (feed == null) {
        		// check if new feedId exists in Feed collection. If not, insert it into Feed collection
        		
        		// Exception: this may be an error condition if article should only use existing feedId
        		
        		feed = new Feed().withFeedId(feedId).withDescription("Feed with feedId:"+feedId);
        		feedService.create(feed);
        	}
        	FeedArticle feedArticle = new FeedArticle().withArticleId(articleId).withFeedId(feedId);
        	feedArticleService.create(feedArticle);
        	LOGGER.info("Created a new feedArticle entry with information: {}", feedArticleService);
        }

        return createdArticle;
    }

    /**
     * Delete the subscription for the given user 
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/subscription/userId/{userId}", method = RequestMethod.DELETE)
    Subscription delete(@PathVariable("userId") String userId) {
        LOGGER.info("Deleting subscription entry with id: {}", userId);

        // TODO: check if it is a valid user

        // delete the user's subscription
        Subscription deleted = subscriptionService.deleteByUserId(userId);
        LOGGER.info("Deleted subscription entry with information: {}", deleted);

        return deleted;
    }

    /**
     * Get the subscription for the given userId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/subscription/userId/{userId}", method = RequestMethod.GET)
    Subscription findByUserId(@PathVariable("userId") String userId) {
        LOGGER.info("Finding subscription entry with userId: {}", userId);

        Subscription subscriptionEntry = subscriptionService.findByUserId(userId);
        LOGGER.info("Found subscription entry with information: {}", subscriptionEntry);

        return subscriptionEntry;
    }
    
    /**
     * Get the list of feeds for a user with the given userId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/feed/userId/{userId}", method = RequestMethod.GET)
    List<Feed> findFeedsByUserId(@PathVariable("userId") String userId) {
        LOGGER.info("Finding list of feeds with userId: {}", userId);

        Subscription subscription = subscriptionService.findByUserId(userId);
        List<String> feedIds = subscription.getFeedIds();
        List<Feed> feeds = new ArrayList<Feed>();
        for (String feedId:feedIds) {
        	Feed feed = feedService.findByFeedId(feedId);
        	feeds.add(feed);	
        }
        LOGGER.info("Found list of feedIds with information: {}", feedIds);

        return feeds;
    }
    
    
    /**
     * Get the list of articles for the given list of feeds
     * @param userId
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/v1/article/feeds/{feedIdsCsv}", method = RequestMethod.GET)
    List<Article> findArticlesByFeedIds(@PathVariable("feedIdsCsv") String feedIdsCsv) {
    	LOGGER.info("Finding article entries with feeds: {}", feedIdsCsv);
        List<String> feedsToQuery = Arrays.asList(feedIdsCsv.split(","));
        LOGGER.info("Finding article entries with feedIds: {}", feedsToQuery);
        List<Article> articleEntries = articleService.findByFeedIdsIn(feedsToQuery);
        LOGGER.info("Found article entries with information: {}", articleEntries);

        return articleEntries;
    }
    
    /**
     * Update a user's subscription
     * @param userEntry
     * @param id
     * @return
     */
    @RequestMapping(value = "/feedapi/ext/subscription/userId/{userId}", method = RequestMethod.PUT)
    Subscription update(
    		@RequestBody @Valid Subscription subscriptionEntry, 
    		@PathVariable("userId") String userId) {
        LOGGER.info("Updating subscription entry with information: {}", subscriptionEntry);
        
        // TODO: check if it is a valid user
        // assert subscriptionEntry is for the user
        
        // update a subscription
        Subscription updated = subscriptionService.update(subscriptionEntry);
        LOGGER.info("Updated subscription entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleSubscriptionNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
