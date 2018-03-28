#!/bin/bash

set -x

data='{"userId":"userId_1", "feedIds":["feedId_1", "feedId_2"]}'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/subscription/userId/userId_1"

data='{"userId":"userId_2", "feedIds":["feedId_1", "feedId_3"]}'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/subscription/userId/userId_2"

data='{"userId":"userId_3", "feedIds":["feedId_1", "feedId_5"]}'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/ext/v1/subscription/userId/userId_3"

