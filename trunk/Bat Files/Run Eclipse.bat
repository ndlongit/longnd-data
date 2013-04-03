@echo off

set ECLIPSE_HOME="E:\Eclipse\eclipse-galileo 3.5"
::set ECLIPSE_HOME="E:\Eclipse\eclipse-helios 3.6"
::set WORK_SPACE="D:\Workspace\eurobase-cms"
::set WORK_SPACE="D:\Workspace\common"

::set WORK_SPACE="D:\Workspace\NXTM"
set WORK_SPACE="D:\Workspace\research"

cd /d %ECLIPSE_HOME% 
set OPTIONS =
::set OPTIONS = %OPTIONS% -clean
start eclipse.exe %OPTIONS% -data %WORK_SPACE% -showlocation %WORK_SPACE%
