#!/usr/bin/env bash
echo "Starting build image process..."

docker build --platform linux/arm64 -t mainhub_microservice .

echo "Tagging new image..."

docker tag mainhub_microservice localhost:5000/mainhub_microservice

echo "Uploading new image to registry..."

docker push localhost:5000/mainhub_microservice

echo "Build image process complete"