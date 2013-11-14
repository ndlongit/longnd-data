@echo off

set FILE_NAME="File-list.txt"

copy /Y nul %FILE_NAME%

for /d /r %%i in (*) do (
	echo %%i >> %FILE_NAME%
)	
pause