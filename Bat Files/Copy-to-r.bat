@echo off

set FILE="desktop.ini"

for /d /r %%i in (*) do (
	if not %%i == .svn ( 
		xcopy /Y /h %FILE% "%%i\"
	)
)	
pause