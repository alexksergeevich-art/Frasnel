#!/usr/bin/env bash
set -e
# If a real gradle wrapper jar/script exists, prefer it
if [ -x ./gradlew.real ]; then
  exec ./gradlew.real "$@"
fi
# Use dockerized Gradle as a reliable fallback
docker run --rm -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:8.6 gradle "$@"
