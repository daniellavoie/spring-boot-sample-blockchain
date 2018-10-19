#!/bin/bash

SRC=src
CI=ci
BUILD=build

cd $SRC && \
  ./mvnw -B package && \
  cd .. && \
  cp -a $SRC/* $BUILD