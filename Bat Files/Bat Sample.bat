mvn install:install-file -DgroupId=com.sencha.gxt -DartifactId=gxt-gwt22 -Dversion=2.2.5 -Dpackaging=jar -Dfile=gxt-gwt22-2.2.5.jar

::@echo off

:: hibernate
%windir%\System32\rundll32.exe powrprof.dll,SetSuspendState 

:: Echo New line
echo.
::Clear file content
 copy /-Y nul %FILE_NAME%

:: if [%1]==[] echo Empty
if "%~1"=="" echo Empty 2


SET Choice=
SET /P Choice=Type the letter and press Enter: 
:: The syntax in the next line extracts the substring

::IF /I '%Choice%'=='A' 
Echo %Choice%


SET Time=
SET /P Time=Nhap vap thoi gian (tinh bang giay): 

Shutdown -f -s -t %Time%


set filename=Test.batrfgr
IF EXIST %filename% (
	echo file name exits.
) ELSE (
	echo filename missing.
)


:: check if a directory exists

if exist F:\Temp\nul (
echo exist
) else (
echo Not exist
)


::for /d %%i in (*) do attrib -h "%%~fi" /s /d

for /d %%i in (*) do (
echo "%%~fi"
::echo.
)

:: Set CLASSPATH - Begin
setlocal ENABLEDELAYEDEXPANSION

::for %%i in (.\lib\*.jar) do (set CLASSPATH=!CLASSPATH!; %%~si)
for %%i in (*) do (set CLASSPATH=!CLASSPATH!; %%~si)

echo %CLASSPATH%

endlocal
:: Set CLASSPATH - End

:: Run HSQLDB with a dbname
java org.hsqldb.util.DatabaseManager -cp <classpath> -url jdbc:hsqldb:<File_Path>/<DB_Name>
::Ex: java -cp ./hsql.jar org.hsqldb.util.DatabaseManager -url jdbc:hsqldb:D:/Data/Document/liferay-portal-5.2.3/liferay-portal-5.2.3/data/lportal

::pause

:: Exit current bat file, not CMD
Exit /b

goto END
:END

::Escape
%	==> %%
For escape special characters (<, >)  ==> ^ , for example ^<, ^>

::String Length - Begin
set MyVar=MyHome
:: Send the length of the variable %MyVar%
:: to the variable %length%
set #=%MyVar%
set length=0
:loop
if defined # (set #=%#:~1%&set /A length += 1&goto loop)
echo MyVar is %length% characters long!
::String Length - End

::Left String
set str=politic
set str=%str:~0,4% :: --> poli

::Right String
set str=politic
set str=%str:~-4% :: --> itic
pause