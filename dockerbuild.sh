#/bin/sh

version=latest

docker build -f  download-engine/Dockerfile -t download-engine:$version --no-cache .
docker build -f  validation-engine/Dockerfile -t validation-engine:$version --no-cache .
docker build -f  parser-engine/Dockerfile -t parser-engine:$version --no-cache .
docker build -f  aggregation-engine/Dockerfile -t aggregation-engine:$version --no-cache .
docker build -f  etl-manager/Dockerfile -t etl-manager:$version --no-cache .
docker build -f  launcher/Dockerfile -t launcher:$version --no-cache .

