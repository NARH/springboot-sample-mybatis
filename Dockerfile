# use alpine as base image
FROM openjdk:8-jdk-alpine

RUN echo 'http://dl-cdn.alpinelinux.org/alpine/edge/testing' >> /etc/apk/repositories
RUN apk update --no-cache
RUN apk add openrc --no-cache
RUN apk add spring-boot-openrc --no-cache

# create directory for application
RUN mkdir /app
WORKDIR /app

# jar target
ENV JAR_TARGET "build/libs/springboot-sample-mybatis-boot.war"

ADD ${JAR_TARGET} /app/springboot-sample-mybatis-boot.war
ADD springboot-sample-mybatis-boot.conf /app/springboot-sample-mybatis-boot.conf
ADD springboot-sample-mybatis-boot.rc.conf /etc/conf.d/springboot-sample-mybatis-boot
RUN /bin/ln -s /etc/init.d/spring-boot /etc/init.d/springboot-sample-mybatis-boot
RUN mkdir -p /run/openrc/
RUN touch /run/openrc/softlevel

# set entrypoint to execute spring boot application
#ENTRYPOINT ["/etc/init.d/springboot-sample-mybatis-boot","start"]
#ENTRYPOINT ["rc-service","springboot-sample-mybatis-boot","start"]
#ENTRYPOINT ["sh","-c","java -jar /app/springboot-sample-mybatis-boot.war"]

EXPOSE 8080
CMD "rc-update add springboot-sample-mybatis-boot boot"