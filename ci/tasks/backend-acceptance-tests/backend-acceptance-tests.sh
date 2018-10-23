#!/bin/sh

set -x

SRC=src
SRC_CI=src-ci
BACKEND_URL=$(cat backend-url/backend-url)

if [ -z "$PROFILE" ]; then
  PROFILE=default
fi

ENVIRONMENT_FILE="$SRC_CI/ci/tasks/backend-acceptance-tests/profiles/$PROFILE"
  
if [ -f $ENVIRONMENT_FILE ]; then
  . $ENVIRONMENT_FILE
fi

echo "Launching Backend Acceptance Tests on $ENVIRONMENT environment."

cd $SRC && \
  BLOCKCHAIN_BACKEND_URL=$BACKEND_URL ./mvnw --file acceptance-tests/pom.xml --activate-profiles acceptanceTests test