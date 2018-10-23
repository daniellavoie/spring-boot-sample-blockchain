#!/bin/sh

set -x 

SRC=src
FRONTEND_URL=$(cat app-url/app-url)

if [ -z "$PROFILE" ]; then
  PROFILE=default
fi

ENVIRONMENT_FILE="src-ci/ci/tasks/backend-acceptances-tests/profiles/$PROFILE"
  
if [ -f $ENVIRONMENT_FILE ]; then
  source $ENVIRONMENT_FILE
fi

echo "Launching Frontend Acceptance Tests on $ENVIRONMENT environment."

cd $SRC/acceptance-tests/src/test/webapp && \
  npm install && \
  FRONTEND_URL=$FRONTEND_URL \
  npm test