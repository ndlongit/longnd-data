@echo off

set SERVER_NAME=localhost
set SERVER_PORT=9001 
set DB_NAME=t5demo_db
set DB_PATH=DB
set HSQL_JAR_FILE=hsqldb-1.8.0.10.jar

start javaw -cp %HSQL_JAR_FILE% org.hsqldb.util.DatabaseManagerSwing %DB_URL%
