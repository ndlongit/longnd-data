@echo off

set FILE_NAME="File-list.txt"

copy /-Y nul %FILE_NAME%

for /d /r %%a in (*) do (
	echo %%a >> %FILE_NAME%
)	
pause