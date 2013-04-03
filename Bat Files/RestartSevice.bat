@echo off

set SERVICE_NAME="ColdFusion 8 Application Server"
net stop %SERVICE_NAME%
net start %SERVICE_NAME%

pause 