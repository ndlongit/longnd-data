@echo off

set SERVER_NAME=localhost
set SERVER_PORT=9001 
set DB_NAME=t5demo_db
set DB_PATH=DB
set HSQL_JAR_FILE=d:\Libraries\Maven Repository\hsqldb\hsqldb\1.8.0.10\hsqldb-1.8.0.10.jar

REM ------------------------------------------No Change------------------------------------------------
set DB_URL=--url jdbc:hsqldb:hsql://%SERVER_NAME%/%DB_NAME%
set DB_STORE=file:"%DB_PATH%"\%DB_NAME%
REM ---------------------------------------------------------------------------------------------------