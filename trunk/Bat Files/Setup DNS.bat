@echo off

set CONNECTION_NAME="Local Area Connection"
	
:SELECT_OPTION
Echo Chon 1 option:
Echo  1. Static DNS
Echo  2. Dynamic DNS

SET /P OPTION=

if [%OPTION%] == [1] (
	
	REM Static DNS
	netsh interface ip set dns %CONNECTION_NAME% static 8.8.8.8
	netsh interface ip add dns %CONNECTION_NAME% 8.8.4.4
) else if [%OPTION%] == [2] ( 
	
	REM Dynamic DNS
	netsh interface ip set dns %CONNECTION_NAME% DHCP
) else (
	Echo Ban phai chon 1 so 1 hoac 2.
	Goto SELECT_OPTION
)

pause
