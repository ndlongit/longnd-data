cd %~dp0

rem set TO_DIR=c:\opt\apache\htdocs\
set TO_DIR=C:\inte-dev\apache\apache-tomcat-7.0.53\webapps\ROOT\

xcopy /S /I /D /Y WebContent\css %TO_DIR%css
xcopy /S /I /D /Y WebContent\img %TO_DIR%img
xcopy /S /I /D /Y WebContent\js  %TO_DIR%js
