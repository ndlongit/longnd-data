@echo off

set SERVER_NAME=localhost
set SERVER_PORT=9001 
set DB_NAME=t5demo
set DB_PATH=DB
set HSQL_JAR_FILE=D:\libraries\.m2\repository\hsqldb\hsqldb\1.8.0.10\hsqldb-1.8.0.10.jar

REM ------------------------------------------No Change------------------------------------------------
set DB_URL=--url jdbc:hsqldb:hsql://%SERVER_NAME%/%DB_NAME%
set DB_STORE=file:"%DB_PATH%"\%DB_NAME%
REM ---------------------------------------------------------------------------------------------------

Echo Chon 1 option:
Echo  1.HSQL Server
Echo  2.HSQL Tool
Echo  3.Both

:SELECT_OPTION
SET /P OPTION=Chon 1 so tu 1 den 3:

if [%OPTION%] == [1] (
  start java -cp %HSQL_JAR_FILE% org.hsqldb.Server -port %SERVER_PORT% -database.0 %DB_STORE% -dbname.0 %DB_NAME%
) else if [%OPTION%] == [2] (
  start javaw -cp %HSQL_JAR_FILE% org.hsqldb.util.DatabaseManagerSwing %DB_URL%
) else if [%OPTION%] == [3] (
  start java -cp %HSQL_JAR_FILE% org.hsqldb.Server -port %SERVER_PORT% -database.0 %DB_STORE% -dbname.0 %DB_NAME%
  start javaw -cp %HSQL_JAR_FILE% org.hsqldb.util.DatabaseManagerSwing %DB_URL%
) else (
  Echo Ban phai chon 1 so tu 1 den 3.
  Goto SELECT_OPTION
)
