#!/bin/bash

set -x

data='{"feedId":"feedId_4", "topics":["topic1", "topic2", "topic3"], "description":"description for feedId_4" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

data='{"feedId":"feedId_5", "topics":["topic1", "topic2", "topic3", "topic5"], "description":"description for feedId_4" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

