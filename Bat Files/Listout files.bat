@echo off

set FILE_NAME="File-list.txt"

::echo. > %FILE_NAME%
copy /-Y nul %FILE_NAME%

for %%i in (*.bat) do (
	echo %%i >> %FILE_NAME%
) 
pause