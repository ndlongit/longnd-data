@Echo off

REM Backup DB (use gzip to compress output file): mysqldump -u [uname] -p[pass] [dbname] | gzip -9 > [backupfile.sql.gz]
REM unzip: gunzip [backupfile.sql.gz]

ECHO Setup data
ECHO.

REM=============================== Login info =============================
SET LOGIN_NAME=root
SET LOGIN_PASSWORD=admin
REM========================================================================

REM=============================== Options ================================
SET COMMAND_OPTIONS=--max_allowed_packet=512M
REM========================================================================

REM================================ Data info =============================
SET DB_NAME=osmozdev
SET SCRIPT_FILE="D:\Projects\Osmoz\Document\rhinfos.sql"
REM========================================================================

ECHO Executing. Please wait....
ECHO.

mysql --user=%LOGIN_NAME% --pass=%LOGIN_PASSWORD% %COMMAND_OPTIONS% %DB_NAME% < %SCRIPT_FILE%

ECHO ----------------------------- DONE ----------------------------------

pause
:END