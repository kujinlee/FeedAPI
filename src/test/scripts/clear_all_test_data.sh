#!/bin/bash
./get_all_articles.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_article_by_id.sh $line; done
./get_all_feedarticles.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_feedarticle_by_id.sh $line; done
./get_all_subscriptions.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_subscription_by_id.sh $line; done
./get_all_subscriptions.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_subscription_by_id.sh $line; done
./get_all_users.sh 2>/dev/null |python -mjson.tool |grep id |cut -d"\"" -f4 |while read line; do ./delete_user_by_id.sh $line; done
