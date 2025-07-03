#!/bin/bash
# Simple network validation for TDengine cluster
# Usage: ./check_cluster.sh host1 host2 ...

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 host1 [host2 ...]" >&2
  exit 1
fi

for host in "$@"; do
  echo "Checking $host..."
  if ! ping -c 1 "$host" >/dev/null 2>&1; then
    echo "Error: cannot reach $host" >&2
  else
    echo "$host reachable"
  fi
  nc -z "$host" 6030 >/dev/null 2>&1 && echo "port 6030 open on $host" || echo "port 6030 closed on $host"
  echo
done
