# FeedAPI with Mongodb

## Design:
1. User: {userId, name}
        - User can subscribe one or more feeds the user want to follow. User can find its listOfFeed via Subscription collection in mongoDB. Once listOfFeed are found for the given user, all the articles associated with found listOfFeed can also be retrieved from FeedArticle collection

2. Feed: {feedId, description}
        - Feed is marker (ie, tag) for article. Articles for a given feed can be found from FeedArticle collection in mongoDB

3. Article: {articleId, listOfFeed, content}

        - Article is contents with listOfFeed attached. When article is fed to FeedAPI, it finds relevant Feeds from its listOfFeed attribute. One new article will insert one of more FeedArticle

4. Subscription: {userId, listOfFeed}
       - Subscription is association between user and feed. A given user has listOfFeed associated.

5. FeedArticle: {feedId, articleId} 
        - FeedArticle is association between a feed and an article. 

## Operations:

  1. Subscribe/Unsubscribe a User to a Feed (external_subscribe_users.sh)
        - read the user from User collection
            - If the user does not exist, ask user's information and create the user and user's subscription into User and Subscription collection
            - If the user exists
                - read the user's subscription from Subscription collection
                - update/delete the the user's Subscription to Subscription collection
      
  2. Add Articles to a Feed (external_add_articles.sh)
        - For each new articles  
            - create an Article and insert into Article collection  
            - create FeedArticle into FeedArticle collection for the newly created article  
                - if the Feed is not in Feed collection, create new Feed and insert into Feed collection
  
  3. Get all Feeds a Subscriber is following (external_get_feeds_by_userId.sh)
        - read listOfFeed for the user (subscriber) from Subscription collection 
  
  4. Get Articles from the set of Feeds a Subscriber is following (external_get_articles_by_feedIds.sh)
        - read listOfFeed for the user (subscriber) from Subscription collection  
        - read articles for the listOfFeed from FeedArticle collection  
	
  5. You can run this application by using the following command:
        - mvn clean spring-boot:run
    
## Test with scripts:
 1. move to scripts directory  
```
   $ cd /FeedAPI/src/test/scripts/
```
 2. Running app:   
```
   $ ./run_feedapi.sh
```
 3. clear all test data: 
```
   $ ./clear_all_test_data.sh
```
 4. Subscribe users to some feeds
```
   $ ./external_subscribe_users.sh

   # to check saved Users
   $ ./internal/get_all_users.sh 

   # to check saved Subscriptions
   $ ./internal/get_all_subscriptions.sh
```
 5. Add Articles to a Feed
```
   $ ./external_add_articles.sh  
   $ ./external_add_articles2.sh

   # to check saved Articles
   $ ./internal/get_all_articles.sh 

   # to check saved FeedArticles
   $ ./internal/get_all_feedarticles.sh 
```
 6. Get all Feeds a Subscriber is following
```
   # checking feeds userId_1 is following
   $ ./external_get_feeds_by_userId.sh userId_1 
```
 7. Get Articles from the set of Feeds a Subscriber is following
```
   # userId_1 is following feedId_1 and feedId_2
   $ ./external_get_articles_by_feedIds.sh feedId_1,feedId_2
```
    