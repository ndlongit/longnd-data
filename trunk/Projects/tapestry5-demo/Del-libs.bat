@echo off

cd /d module-web\target\t5demo\WEB-INF

rd /S/Q classes
mkdir "classes/META-INF"

cd /d "lib"
del /Q module-*.jar

pause