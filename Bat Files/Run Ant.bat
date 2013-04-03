@echo off

set BUILD_FILE="D:/Data/Source Forge/data-store/cms-ant-util/build-util.xml"
set FROM_DIR="D:/Working/Eurobase-CMS/Code/EurobaseCMS"
set TO_DIR="D:/Working/Eurobase-CMS/Code/EurobaseCMS-2"

::set BUILD_FILE="E:/Long/data-store/cms-ant-util/build-util.xml"
::set FROM_DIR="E:/Long/Working/Eurobase-CMS/Code/EurobaseCMS"

set TARGET=copy-files

set LOG_FILE="Out-log.log"
if exist %LOG_FILE% del /Q %LOG_FILE%

::set ANT_HOME=


"%ANT_HOME%"\bin\Ant -buildfile %BUILD_FILE% %TARGET% -DfromDir="%FROM_DIR%" -DtoDir="%TO_DIR%" >> %LOG_FILE%