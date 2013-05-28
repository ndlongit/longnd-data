@echo off

cd /d ..\module-site-01\target\site-01\WEB-INF

rd /S/Q classes
mkdir classes/META-INF

cd /d "lib"
del /Q module-*.jar

pause