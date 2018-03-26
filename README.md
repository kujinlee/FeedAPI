Mongodb is chosen for DB

Design:
User: {userId, name}
Feed: {feedId, list of topics}
Article: {articleId, list of topics}
Subscription: {userId, list of topics}
FeedArticle: {feedId, articleId}

- User can subscribe one or more topics the user want to follow. User can find its subscription via Subscription collection in mongoDB
- Feed is a grouping of article by one of more topics that it is configured. feed can find articles that belongs to the feed via FeedArticle collection in mongoDB
- Article is article with topics attached. When article is feed to FeedAPI, it finds relevant Feed for the topics of the Article and insert one of more FeedArticle

- (1) Finding all Articles for a given user is following:
      a) find listOfTopics for the user
      b) find Articles for the listOfTopics from Article Collection
      
      NOTE: I noticed that that this is not the intended way. To fix this, change Subscription as {userId, list of feedId}. User will have to choose which feeds to subscribe based on configurations of Feed. Alternatively, Feed can be defined as {feedId, topic} so that feed becomes same as topic in cocept. 
      
 - (2) Finding all Articles from set of Feed
      a) find listOfTopics for the Feed
      b) find Articles for the listOfTopics via Article collection in mongoDB



You can run this application by using the following command:

    mvn clean spring-boot:run
    
 Scripts can be used for
 (1) Running app:   
   /FeedAPI/src/test/scripts/run_feedapi.sh
 (2) Populating initial values: 
   /FeedAPI/src/test/scripts/post*.sh
 (3) Testing APIs
  /FeedAPI/src/test/scripts/get*.sh
    