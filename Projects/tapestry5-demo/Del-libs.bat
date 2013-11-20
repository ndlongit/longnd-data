@echo off

cd /d module-web\target\my-web\WEB-INF

rd /S/Q classes
mkdir "classes/META-INF"

cd /d "lib"
del /Q module-*.jar

pause