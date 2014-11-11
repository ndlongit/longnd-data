@echo off

set FILE_NAME="File-list.txt"

copy /-Y nul %FILE_NAME%

for /d %%a in (*) do dir /ad /on /s /b "%%a" >> %FILE_NAME%
pause