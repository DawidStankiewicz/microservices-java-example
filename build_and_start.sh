#!/bin/bash

echo "Building Spring Boot applications..."

cd configuration-server && mvn clean package -DskipTests && cd ..
cd discovery-service && mvn clean package -DskipTests && cd ..
cd gateway-server && mvn clean package -DskipTests && cd ..
cd pool-service && mvn clean package -DskipTests && cd ..
cd results-service && mvn clean package -DskipTests && cd ..

echo "Running docker-compose..."
docker-compose up --build

