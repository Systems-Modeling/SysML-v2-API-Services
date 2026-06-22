#!/bin/sh
# Pass Play configuration as JVM -D flags so the container's postgres service
# name ("postgres") is used instead of the hardcoded "localhost" in persistence.xml.
# All values can be overridden via docker-compose environment variables.
set -e

exec ./bin/sysml-v2-api-services \
  -Dpidfile.path=/dev/null \
  -Dhttp.port=9000 \
  -Dplay.http.secret.key="${PLAY_SECRET:-SysMLv2APIServicesLocalDockerSecretKeyDoNotUseInProd}" \
  -Ddb.default.driver=org.postgresql.Driver \
  -Ddb.default.url="${DB_URL:-jdbc:postgresql://postgres:5432/sysml2}" \
  -Ddb.default.username="${DB_USERNAME:-postgres}" \
  -Ddb.default.password="${DB_PASSWORD:-mysecretpassword}"
