#!/bin/sh

set -x	

PATH=path
CF_SPACE_FILE=cf-space/cf-space

if [ -z "$CF_SPACE" ]; then
  if [ ! -z "$CF_SPACE_PREFIX" ]; then
    CF_SPACE="$CF_SPACE_PREFIX-"
  fi
  
  CF_SPACE=$CF_SPACE$(pwgen -s -1)

  echo "Generated a random space named $CF_SPACE."
fi

echo "$CF_SPACE" > CF_SPACE_FILE

cleanSpace() {
  cf delete -f $APP_NAME
}

createService() {
  #cf create-service $DB_SERVICE_NAME $DB_SERVICE_PLAN $DB_NAME
}

loginAndTargetSpace(){
  cf api $CF_API --skip-ssl-validation && \
  cf auth $CF_USER $CF_PASSWORD
  
  cf create-space -o $CF_ORG $CF_SPACE
  cf target -o $CF_ORG -s $CF_SPACE

  # TODO - return error
}

pushApplication() {
  PARAMS="--no-start -p $PATH $APP_NAME -d $APP_DOMAIN -b $BUILDPACK"

  if [ ! -z "$APP_HOSTNAME" ]; then
    PARAMS="$PARAMS -n $APP_HOSTNAME"
  else
    PARAMS="$PARAMS --random-route"
  fi

  cf push $PARAMS

  if [ ! -z "$DB_NAME" ]; then
    cf bind-service $APP_NAME $DB_NAME
  fi

  cf start $APP_NAME

  # TODO - return error
}

# TODO - Check for errors
loginAndTargetSpace

# TODO - Check for errors
cleanSpace

# TODO - Check for errors
createService

# TODO - Check for errors
pushApplication