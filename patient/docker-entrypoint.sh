#!/bin/bash
set -e

echo "Entrypoint script started."

# Start MySQL
/docker-entrypoint.sh "$@"

# Check database is empty
if [ ! -f /var/lib/mysql/medilabo/patient.ibd ]; then
  echo "Initializing database with data..."
  mysql -h localhost -u root -ppassword medilabo < /docker-entrypoint-initdb.d/2-data.sql
else
  echo "Database already initialized. Skipping data insertion."
fi
