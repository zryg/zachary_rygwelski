#!/bin/bash
  
docker run --detach \
  --name postgres-db \
  --env POSTGRES_PASSWORD=gqhcktqdrxulno \
  --env POSTGRES_USER=eOTADeYDgamAAiKAeF-QkW3ekB \
  --publish 5432:5432 \
  postgres:9.4.4
