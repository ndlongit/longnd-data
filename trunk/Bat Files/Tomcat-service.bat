::@echo off

set TOMCAT_HOME=d:\Software\Tomcat\apache-tomcat-7.0.57
set COMMAND="%TOMCAT_HOME%/bin/tomcat7"

set SERVICE_NAME=Tomcat7.0.x
set DISPLAY_NAME=Tomcat 7.0.57
set DESCRIPTION=DESCRIPTION
::STARTUP_MODE=auto | manual
set STARTUP_MODE=auto

%COMMAND% //IS//%SERVICE_NAME% --DisplayName="%DISPLAY_NAME%" --Description="%DESCRIPTION%"
:: --Startup=%STARTUP_MODE%

::Delete Service
::%COMMAND% //DS//%SERVICE_NAME%

pause