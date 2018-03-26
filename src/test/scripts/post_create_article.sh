#!/bin/bash

set -x

data='{"articleId":"articleId_1", "topics":["topic1", "topic2"], "content":"content for articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/article/"

data='{"articleId":"articleId_2", "topics":["topic1", "topic3"], "content":"content for articleId_2" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/article/"

data='{"articleId":"articleId_3", "topics":["topic1", "topic5"], "content":"content for articleId_3" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/article/"


