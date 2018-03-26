#!/bin/bash

set -x

data='{"feedId":"feedId_1", "topics":["topic1", "topic2", "topic3"], "description":"this is feed 1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"
