# Twitter Coding Challenge

Java implementation of the Twitter Coding Challenge. (Java 8, Junit, Maven)

# Overview

At Twitter, authors of source code changes must get approval from the engineers responsible for the files affected by the change. 
This challenge implements a simplified version of the approval system. 

# Requirements
Java 8, Maven

Maven installation:  
Windows: https://mkyong.com/maven/how-to-install-maven-in-windows/
Mac: brew install maven

# Install
1. Clone the repo (twitter-challenge)
2. cd twitter-challenge
3. Note: The repo_root (given by Twitter) with src, tests and OWNERS file should reside in the twitter-challenge repo (like given in this zip file)
4. Build:> "mvn clean install"
5. Generate jar file:> "mvn clean package assembly:single" - this generates validate_approvals.jar in twitter-challenge\target folder

Note: The jar file is already generated in the target folder.
Main Class: Driver.java
Integration Test: ExecutorTest.java
Rest all are unit tests

# Testing

* cd twitter-challenge
* For automated testing, Run
	1) mvn -Dtest="com.twitter.*Test" test

* For manual testing, Run
  1) java -jar target/validate_approvals.jar --approvers alovelace,ghopper --changed-files src/com/twitter/follow/Follow.java,src/com/twitter/user/User.java
  2) java -jar target/validate_approvals.jar --approvers alovelace --changed-files src/com/twitter/follow/Follow.java
  3) java -jar target/validate_approvals.jar --approvers eclarke --changed-files src/com/twitter/follow/Follow.java
  4) java -jar target/validate_approvals.jar --approvers alovelace,eclarke --changed-files src/com/twitter/follow/Follow.java
  5) java -jar target/validate_approvals.jar --approvers mfox --changed-files src/com/twitter/tweet/Tweet.java
