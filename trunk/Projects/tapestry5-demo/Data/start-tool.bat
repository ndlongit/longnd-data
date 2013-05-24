@echo off

call set-config.bat

start javaw -cp %HSQL_JAR_FILE% org.hsqldb.util.DatabaseManagerSwing %DB_URL%
