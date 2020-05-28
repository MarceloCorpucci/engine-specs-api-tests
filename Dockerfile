FROM maven:3.6.3-jdk-8

LABEL MAINTAINER="Marcelo Corpucci <mcorpucci@gmail.com>"

ENV APP /framework

RUN mkdir $APP
WORKDIR $APP

COPY . .

RUN mvn install -DskipTests