#!/usr/bin/env bash

mkdir services
cd services




spring init \
--boot-version=2.2.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=user-service \
--package-name=com.renga.services.user \
--groupId=com.renga \
--dependencies=web,data-jpa,h2,flyway,actuator,devtools,lombok,postgresql \
--version=1.0.0-SNAPSHOT \
user-service

spring init \
--boot-version=2.2.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=post-service \
--package-name=com.renga.services.post \
--groupId=com.renga \
--dependencies=web,data-jpa,h2,flyway,actuator,devtools,lombok,postgresql \
--version=1.0.0-SNAPSHOT \
post-service

spring init \
--boot-version=2.2.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=composite-service \
--package-name=com.renga.services.composite \
--groupId=com.renga \
--dependencies=web,data-jpa,h2,flyway,actuator,devtools,lombok,postgresql \
--version=1.0.0-SNAPSHOT \
composite-service

cd ..