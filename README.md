# Mongodb is chosen for DB

## Design:
1. User: {userId, name}
2. Feed: {feedId, description}
3. Article: {articleId, listOfFeed, content}
4. Subscription: {userId, listOfFeed}
5. FeedArticle: {feedId, articleId} 

- User can subscribe one or more feeds the user want to follow. User can find its listOfFeed via Subscription collection in mongoDB. Once listOfFeed are found for the given user, all the articles associated with found listOfFeed can also be retrieved from FeedArticle collection

- Feed is marker (ie, tag) for article. Articles for a given feed can be found from FeedArticle collection in mongoDB

- Article is contents with listOfFeed attached. When article is fed to FeedAPI, it finds relevant Feeds from its listOfFeed attribute. One new article will insert one of more FeedArticle

- Subscription is association between user and feed. A given user has listOfFeed associated.

- FeedArticle is association between a feed and an article. 


## Operations:

  1. Subscribe/Unsubscribe a User to a Feed
  (external_subscribe_users.sh)
      1. read the user from User collection
      	1. If the user does not exist, ask user's information and create the user and user's subscription into User and Subscription collection
      	3. If the user exists
		      3. read the user's subscription from Subscription collection
		      2. update/delete the the user's Subscription to Subscription collection
      
  2. Add Articles to a Feed
  (external_add_articles.sh)
      a. For each new articles
        (a1) create an Article and insert into Article collection
        (a2) create FeedArticle into FeedArticle collection for the newly created article
            (a21) if the Feed is not in Feed collection, create new Feed and insert into Feed collection
  
  3. Get all Feeds a Subscriber is following
  (external_get_feeds_by_userId.sh)
      a. read listOfFeed for the user (subscriber) from Subscription collection 
  
  4. Get Articles from the set of Feeds a Subscriber is following
  (external_get_articles_by_feedIds.sh)
      a. read listOfFeed for the user (subscriber) from Subscription collection
      b. read articles for the listOfFeed from FeedArticle collection  
	
You can run this application by using the following command:

    mvn clean spring-boot:run
    
## Test with scripts:
 (0) move to scripts directory
   $ cd /FeedAPI/src/test/scripts/
 (1) Running app:   
   $ ./run_feedapi.sh
 (2) clear all test data: 
   $ ./clear_all_test_data.sh
 (3) Subscribe users to some feeds
   $ ./external_subscribe_users.sh
   # to check saved Users
   $ ./internal/get_all_users.sh 
   # to check saved Subscriptions
   $ ./internal/get_all_subscriptions.sh
 (4) Add Articles to a Feed
   $ ./external_add_articles.sh
   $ ./external_add_articles2.sh
   # to check saved Articles
   $ ./internal/get_all_articles.sh 
   # to check saved FeedArticles
   $ ./internal/get_all_feedarticles.sh 
 (5) Get all Feeds a Subscriber is following
   # checking feeds userId_1 is following
   $ ./external_get_feeds_by_userId.sh userId_1 
 (6) Get Articles from the set of Feeds a Subscriber is following
   # userId_1 is following feedId_1 and feedId_2
   $ ./external_get_articles_by_feedIds.sh feedId_1,feedId_2
    