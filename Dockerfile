FROM maven:3.8.4-jdk-11


WORKDIR /app
COPY . /app

RUN mvn install