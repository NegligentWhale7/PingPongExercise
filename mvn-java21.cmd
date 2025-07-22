@echo off
REM Set JAVA_HOME to Java 21 for this project
set JAVA_HOME=C:\Program Files\Java\jdk-21

REM Run Maven wrapper with the arguments passed to this script
call mvnw.cmd %*
