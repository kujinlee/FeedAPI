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


Operations

  (1) Subscribe/Unsubscribe a User to a Feed
      a) read the user from User collection
      	(a1) If the user does not exist, ask user's information and create the user and user's subscription into User and Subscription collection
      	(a2) If the user exists
		      (a21) read the user's subscription from Subscription collection
		      (a22) update/delete the the user's Subscription to Subscription collection
      
  (2) Add Articles to a Feed
      a) For each new articles
        (a1) create Article into Article collection
        (a2) create FeedArticle into FeedArticle collection for the newly created article
  
  (3) Get all Feeds a Subscriber is following
      a) read listOfFeed for the user (subscriber) from Subscription collection
  
  (4) Get Articles from the set of Feeds a Subscriber is following
      a) read listOfFeed for the user (subscriber) from Subscription collection
      b) read articles for the listOfFeed from FeedArticle collection  
	
You can run this application by using the following command:

    mvn clean spring-boot:run
    
 Scripts can be used for
 (1) Running app:   
   /FeedAPI/src/test/scripts/run_feedapi.sh
 (2) Populating initial values: 
   /FeedAPI/src/test/scripts/post*.sh
 (3) Testing APIs
  /FeedAPI/src/test/scripts/get*.sh
    