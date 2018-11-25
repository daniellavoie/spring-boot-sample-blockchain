#!/bin/sh

SRC=src
BUILD=build

cd $SRC && \
  ./mvnw -B package && \
  cd .. && \
  cp -a $TARGET_PATH $BUILD

if [ ! -z "$DOCKERFILE" ]; then
  cp $DOCKERFILE $BUILD/
fi