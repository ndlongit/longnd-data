@echo off

set JUNCTION_HOME=E:\Utility\Junction
set MAIN_PROJECT=D:\Projects\Elevtr-2.0\Source-Code\elevtr-main
set FILE_REPO_FOLDER=D:\Other\file-repo\TaylorSwift

REM ================================================== No Change ===================================================
set JUNCTION_APP_HOME=%MAIN_PROJECT%\src\setup\file-repo
REM ================================================== No Change ===================================================

"%JUNCTION_HOME%"\junction "%JUNCTION_APP_HOME%" "%FILE_REPO_FOLDER%"

pause