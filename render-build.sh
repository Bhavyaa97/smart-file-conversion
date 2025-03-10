#!/usr/bin/env bash

# Install Java 21
curl -fsSL https://deb.nodesource.com/setup_16.x | bash -
apt-get update && apt-get install -y openjdk-21-jdk

# Set Java Home
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Install Maven
apt-get install -y maven

# Verify Java and Maven Installation
java -version
mvn -version

# Run Maven build
mvn clean install
