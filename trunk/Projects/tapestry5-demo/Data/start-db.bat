@echo off

call set-config.bat

start java -cp "%HSQL_JAR_FILE%" org.hsqldb.Server -port %SERVER_PORT% -database.0 %DB_STORE% -dbname.0 %DB_NAME%

