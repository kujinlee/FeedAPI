#!/bin/bash

set -x

data='{"articleId":"articleId_4", "feedIds":["feedId_1", "feedId_2", "feedId_3"], "content":"content for articleId_4" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/article/"

data='{"articleId":"articleId_5", "feedIds":["feedId_1", "feedId_3", "feedId_4"], "content":"content for articleId_5" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/article/"

data='{"articleId":"articleId_6", "feedIds":["feedId_1", "feedId_5", "feedId_6"], "content":"content for articleId_6" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/article/"


