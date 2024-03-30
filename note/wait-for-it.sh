#!/bin/bash
# wait-for-it.sh

host="$1"
port="$2"
timeout="${3:-30}"

echo "Waiting for $host:$port to become available..."

while ! nc -z "$host" "$port"; do
  sleep 1
  timeout=$((timeout - 1))
  if [ $timeout -eq 0 ]; then
    echo "Timeout occurred. $host:$port is not available."
    exit 1
  fi
done

echo "$host:$port is available."
exit 0
