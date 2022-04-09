#!/bin/bash
set -xe

# Default Gradle settings are not optimal for Android builds, override them
# here to make the most out of the GitHub Actions build servers
GRADLE_OPTS="$GRADLE_OPTS -Xms4g -Xmx4g"
GRADLE_OPTS="$GRADLE_OPTS -XX:+HeapDumpOnOutOfMemoryError"
GRADLE_OPTS="$GRADLE_OPTS -Dorg.gradle.daemon=false"
GRADLE_OPTS="$GRADLE_OPTS -Dorg.gradle.workers.max=2"
GRADLE_OPTS="$GRADLE_OPTS -Dkotlin.incremental=false"
GRADLE_OPTS="$GRADLE_OPTS -Dkotlin.compiler.execution.strategy=in-process"
GRADLE_OPTS="$GRADLE_OPTS -Dfile.encoding=UTF-8"
export GRADLE_OPTS

# Crawl all gradlew files which indicate an Android project
# You may edit this if your repo has a different project structure
for GRADLEW in `find . -name "gradlew"` ; do
    SAMPLE=$(dirname "${GRADLEW}")
    # Tell Gradle that this is a CI environment and disable parallel compilation
    bash "$GRADLEW" -p "$SAMPLE" -Pci --no-parallel --stacktrace $@
done
