FROM java:8

ENV url="http://192.168.89.39:8125/git/root/Demo1_spring_boot_app.git/demo2.properties"
ENV serveruri="http://10.209.25.38:8888"
RUN echo "$url"
RUN echo "$serveruri"

WORKDIR /tmp/code/demo2

ADD ./demo2_redis_mongo/target/demo2_redis_mongo-0.0.1-SNAPSHOT.jar /tmp/code/demo2/demo2_redis_mongo.jar
ADD run.sh /tmp/code/demo2/run.sh

RUN chmod +x /tmp/code/demo2/run.sh

EXPOSE 9003

CMD /tmp/code/demo2/run.sh
