@echo off

REM Note: Using backSlash (\)

setlocal ENABLEDELAYEDEXPANSION

for %%i in (..\lib\*.jar) do (
	set CLASSPATH=%%~si; !CLASSPATH!
)

echo %CLASSPATH%

endlocal