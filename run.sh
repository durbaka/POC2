#!/bin/bash
#mkdir log4j_config

#cd log4j_config
#echo "$url"

wget -O /tmp/code/demo2/log4j.properties "$url"

#/tmp/code/demo1

java -jar demo2_redis_mongo.jar -spring.cloud.config.uri="$serveruri" -Dlog4j.configuration=file:/tmp/code/demo2/log4j.properties
