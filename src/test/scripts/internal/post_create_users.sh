#!/bin/bash

set -x

data='{"userId":"userId_1", "name":"User Name 1" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/user/"

data='{"userId":"userId_2", "name":"User Name 2" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/user/"

data='{"userId":"userId_3", "name":"User Name 3" }'
echo $data
curl -vvv -k  --request POST --header "Content-type:application/json" --data "$data"  "http://localhost:8080/feedapi/user/"
