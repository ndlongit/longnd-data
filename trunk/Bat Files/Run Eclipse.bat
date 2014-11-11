@echo off

::set ECLIPSE_HOME="D:\Software\Eclipse\Eclipse 4.2"
::set ECLIPSE_HOME="d:\Software\Eclipse\Eclipse PHP 4.3"
set ECLIPSE_HOME="d:\Software\Eclipse\Eclipse 4.3 - X64"

set WORK_SPACE="d:\Data-Store\Workspace"
::set WORK_SPACE="d:\Data-Store\PHP-Workspace"

cd /d %ECLIPSE_HOME% 
set OPTIONS=
set OPTIONS=%OPTIONS% -clean
start eclipse.exe %OPTIONS% -data %WORK_SPACE% -showlocation %WORK_SPACE%