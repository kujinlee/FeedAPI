#!/bin/bash

set -x
curl -vvv -k --request DELETE --header "Content-type:application/json" http://localhost:8080/feedapi/subscription/${1}

