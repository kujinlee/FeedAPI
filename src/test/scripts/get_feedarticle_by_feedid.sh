#!/bin/bash

set -x
if [ "$#" -ne 1 ]; then
    echo "Usage $0 <argument>"
    exit -1
fi
curl -vvv -k --request GET --header "Content-type:application/json" http://localhost:8080/feedapi/feedarticle/feedId/${1}

