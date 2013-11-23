::@echo off

SET MODULE_NAME=module-struts2
SET FINAL_NAME=truts2

cd /d %MODULE_NAME%\target\%FINAL_NAME%\WEB-INF
pause
rd /S/Q classes
mkdir "classes/META-INF"

cd /d "lib"
del /Q module-*.jar

pause