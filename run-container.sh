#!/usr/bin/env bash

podman build --no-cache -t docker.io/aalamo/news:1.0 -f news.dockerfile .

sleep 4

podman run --network citadel_citadel-network --env-file ../container-config/.env  -p 8080:8080 news:1.0