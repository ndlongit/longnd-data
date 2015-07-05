@echo off

set ECLIPSE_HOME="d:\Software\Eclipse\eclipse-jee-luna-SR2-win32-x86_64"

set WORK_SPACE="d:\Workspace\common-workspace"
::set WORK_SPACE="d:\Data-Store\PHP-Workspace"

cd /d %ECLIPSE_HOME% 
set OPTIONS=
set OPTIONS=%OPTIONS% -clean
start eclipse.exe %OPTIONS% -data %WORK_SPACE% -showlocation %WORK_SPACE%