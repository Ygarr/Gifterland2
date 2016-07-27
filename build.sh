#!/usr/bin/env bash
mvn clean
sleep 2
mvn spring-boot:run -Dmaven.test.skip=true

