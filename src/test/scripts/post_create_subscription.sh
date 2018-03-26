#!/bin/bash

set -x

data='{"userId":"userId_4", "topics":["topic1", "topic2", "topic3"] }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/subscription/"

data='{"userId":"userId_5", "topics":["topic1", "topic2", "topic3", "topic5"] }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/subscription/"

