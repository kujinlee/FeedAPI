#!/bin/bash
./get_all_subscriptions.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_subscription_by_id.sh $line; done
