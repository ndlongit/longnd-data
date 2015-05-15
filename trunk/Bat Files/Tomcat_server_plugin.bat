@echo off

set WORKSPACE=d:/Projects/qms/workspace
set FOLDER_PATH=%WORKSPACE%/.metadata/.plugins/org.eclipse.wst.server.core/tmp0
::start cd /d %FOLDER_PATH%
:: Not work
::%SystemRoot%\explorer.exe %FOLDER_PATH% 
START %FOLDER_PATH%

::pause