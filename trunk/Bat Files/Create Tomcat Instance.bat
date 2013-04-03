@echo off

::set /P CATALINA_HOME=Nhap vao duong dan den thu muc cai dat Tomcat (CATALINA_HOME):
::set /p INSTANCE_NAME=Nhap vao duong dan den thu muc can tao Tomcat Instance (CATALINA_BASE):

set CATALINA_HOME=d:\Softwares\Tomcat\apache-tomcat-6.0.35
set INSTANCE_NAME=d:\Softwares\Tomcat\Base\apache-tomcat-6.0.35-Base

REM ----------------------------------------------------No Change -------------------------------------------------------------
set STARTUP_FILE=bin\startup.bat
set SHUTDOWN_FILE=bin\shutdown.bat
set ENV_FILE=bin\setenv.bat
REM ---------------------------------------------------------------------------------------------------------------------------

mkdir "%INSTANCE_NAME%"
cd "%INSTANCE_NAME%"

mkdir bin conf logs temp webapps work

copy "%CATALINA_HOME%"\conf\server.xml conf\
copy "%CATALINA_HOME%"\conf\web.xml conf\

echo. > %ENV_FILE%

echo. > %STARTUP_FILE%
echo set CATALINA_HOME="%CATALINA_HOME%">> %STARTUP_FILE%
echo cd .. >> %STARTUP_FILE%
echo set CATALINA_BASE=%%cd%%>> %STARTUP_FILE%
echo call %%CATALINA_HOME%%\%STARTUP_FILE% >> %STARTUP_FILE%

echo. > %SHUTDOWN_FILE%
echo set CATALINA_HOME="%CATALINA_HOME%">> %SHUTDOWN_FILE%
echo call %%CATALINA_HOME%%\%SHUTDOWN_FILE% >> %SHUTDOWN_FILE%

cd..
echo DONE
pause