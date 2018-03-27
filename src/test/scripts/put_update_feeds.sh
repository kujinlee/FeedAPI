#!/bin/bash

set -x

data='{"feedId":"feedId_1", "description":"description for feedId_1" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

data='{"feedId":"feedId_2", "description":"description for feedId_2" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

data='{"feedId":"feedId_3", "description":"description for feedId_3" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

data='{"feedId":"feedId_4", "description":"description for feedId_4" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"

data='{"feedId":"feedId_5", "description":"description for feedId_5" }'
echo $data
curl -vvv -k  --request PUT --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/feed/"