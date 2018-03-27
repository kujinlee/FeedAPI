#!/bin/bash

set -x

data='{"feedId":"feedId_1", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"

data='{"feedId":"feedId_2", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"

data='{"feedId":"feedId_3", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"

data='{"feedId":"feedId_1", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"

data='{"feedId":"feedId_3", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"

data='{"feedId":"feedId_2", "articleId":"articleId_1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feedarticle/"
