::@echo off

set EXTENSION=.bak

for %%i in (*.jpg) do (
	rename "%%~fi" "%%~nxi%EXTENSION%"
) 
pause 