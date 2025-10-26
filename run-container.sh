#!/usr/bin/env bash

echo "Building news container image..."
podman build --no-cache -t docker.io/aalamo/news:1.0 -f news.dockerfile .

echo "checking if mongodb is up..."
# 1. Definir la ruta del archivo compose con $HOME para expansión correcta
COMPOSE_FILE="$HOME/repos/mongodb/docker-compose.yaml"

if podman ps --format '{{.Names}}' | grep -q '^mongodb$'; then
  echo "Mongodb no está corriendo. Iniciando..."
  echo "Mongdb ya está en ejecución."
else
  podman-compose -f "$COMPOSE_FILE" up -d
fi

echo "Starting news container..."
podman run --network citadel_citadel-network --env-file ../container-config/.env  -p 8080:8080 news:1.0