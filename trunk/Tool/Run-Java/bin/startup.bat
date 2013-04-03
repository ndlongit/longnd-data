@echo off

::Note: Using backSlash (\)

REM ======================= Set Main Class to run - Begin. =============================
SET MAIN_CLASS=org.demo.MainClass
REM ======================= Set Main Class to run - End. ===============================

setlocal ENABLEDELAYEDEXPANSION

pushd .
SET CLASS_DIR=..\classes
cd %CLASS_DIR%
set CLASSPATH=%CD%;!CLASSPATH!
popd

for %%i in (..\lib\*.jar) do (
	set CLASSPATH=%%~fi;!CLASSPATH!
)

echo %CLASSPATH%

java -Dapp.config=../conf/AppConfig.properties -cp %CLASSPATH% %MAIN_CLASS%
::java -Dapp-config=../conf/AppConfig.properties -cp ../classes %MAIN_CLASS%

endlocal
::pause