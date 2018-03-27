#!/bin/bash

set -x
curl -vvv -k --request GET --header "Content-type:application/json" http://localhost:8080/feedapi/user

