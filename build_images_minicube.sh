#!/bin/bash

echo "Building Spring Boot applications..."

services=("configuration-server" "discovery-service" "gateway-server" "pool-service" "results-service")

eval $(minikube docker-env)

for service in "${services[@]}"; do
  cd "$service" && docker image build -t "$service" . && cd ..
done


