Mongodb is chosen for DB

Design:
User: {userId, name}
Feed: {feedId, description}
Article: {articleId, listOfFeed, content}
Subscription: {userId, listOfFeed}
FeedArticle: {feedId, articleId} 

- User can subscribe one or more feeds the user want to follow. User can find its listOfFeed via Subscription collection in mongoDB. Once listOfFeed are found for the given user, all the articles associated with found listOfFeed can also be retrieved from FeedArticle collection

- Feed is marker (ie, tag) for article. Articles for a given feed can be found from FeedArticle collection in mongoDB

- Article is contents with listOfFeed attached. When article is fed to FeedAPI, it finds relevant Feeds from its listOfFeed attribute. One new article will insert one of more FeedArticle

- Subscription is association between user and feed. A given user has listOfFeed associated.

- FeedArticle is association between a feed and an article. 

  (1) Finding all Articles for a given user is following:
      a) find listOfFeed for the user from its subscription
      b) find Articles for the listOfFeed from FeedArticle Collection
         
  (2) Finding all Articles from listOfFeed
      a) find listOfArticleId for the listOfFeed from FeedArticle collection
      b) find listOfArticle for teh listOfArticleId from Article collection
  (3) Subscribe a user to listOfFeed
      a) create of update subscription for the given user and listOfFeed
	
You can run this application by using the following command:

    mvn clean spring-boot:run
    
 Scripts can be used for
 (1) Running app:   
   /FeedAPI/src/test/scripts/run_feedapi.sh
 (2) Populating initial values: 
   /FeedAPI/src/test/scripts/post*.sh
 (3) Testing APIs
  /FeedAPI/src/test/scripts/get*.sh
    