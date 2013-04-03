@echo off

REM -------------------------------------------------------------------------
set OUTPUT_FOLDER=D:\Data-Store\Bat Files\java-class
::rd /S /Q %OUTPUT_FOLDER%

set BASE_PACKAGE=org.java.demo
set MODEL_CLASS=Category
REM --------------------------------------------------------------------------

REM ==========================================================================
set MODEL_PACKAGE=model.domain
set DAO_PACKAGE=dao.domain
set SERVICE_PACKAGE=service.domain
set BASIC_MODEL=BasicEntity
set BASIC_SERVICE=BasicService
set BASIC_DAO=BasicDao
REM ==========================================================================

REM ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
set MODEL_EXTENSION=.java
REM ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

REM ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set BASIC_FOLDER=
for /F "delims=. tokens=1,2,3*" %%i in ("%BASE_PACKAGE%") do (
	set BASIC_FOLDER=%%i\%%j\%%k
)

set MODEL_FOLDER=
for /F "delims=. tokens=1,2*" %%i in ("%MODEL_PACKAGE%") do (
	set MODEL_FOLDER=%%i\%%j
)

set DAO_FOLDER=
for /F "delims=. tokens=1,2*" %%i in ("%DAO_PACKAGE%") do (
	set DAO_FOLDER=%%i\%%j
)

set SERVICE_FOLDER=
for /F "delims=. tokens=1,2*" %%i in ("%SERVICE_PACKAGE%") do (
	set SERVICE_FOLDER=%%i\%%j
)

set JAVA_FOLDER=%OUTPUT_FOLDER%\src\main\java\%BASIC_FOLDER%
set RESOURCE_FOLDER=%OUTPUT_FOLDER%\src\main\resources\%BASIC_FOLDER%

if not exist "%JAVA_FOLDER%\%MODEL_FOLDER%" (
	mkdir "%JAVA_FOLDER%\%MODEL_FOLDER%"	
)
if not exist "%JAVA_FOLDER%\%DAO_FOLDER%\impl" (
	mkdir "%JAVA_FOLDER%\%DAO_FOLDER%\impl"	
)
if not exist "%JAVA_FOLDER%\%SERVICE_FOLDER%\impl" (
	mkdir "%JAVA_FOLDER%\%SERVICE_FOLDER%\impl"
)

set MODEL_FILE="%JAVA_FOLDER%\%MODEL_FOLDER%\%MODEL_CLASS%%MODEL_EXTENSION%"
copy /Y nul %MODEL_FILE%
echo package %BASE_PACKAGE%.%MODEL_PACKAGE%;>> %MODEL_FILE%
echo public class %MODEL_CLASS% implements %BASIC_MODEL%^<Long^>{}>> %MODEL_FILE%

set DAO_FILE="%JAVA_FOLDER%\%DAO_FOLDER%\%MODEL_CLASS%Dao%MODEL_EXTENSION%"
copy /Y nul %DAO_FILE%
echo package %BASE_PACKAGE%.%DAO_PACKAGE%;>> %DAO_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_FILE%
echo public interface %MODEL_CLASS%Dao extends %BASIC_DAO%^<%MODEL_CLASS%, Long^> {}>> %DAO_FILE%

set DAO_IMPL_FILE="%JAVA_FOLDER%\%DAO_FOLDER%\impl\%MODEL_CLASS%DaoImpl%MODEL_EXTENSION%"
copy /Y nul %DAO_IMPL_FILE%
echo package %BASE_PACKAGE%.%DAO_PACKAGE%.impl;>> %DAO_IMPL_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_IMPL_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %DAO_IMPL_FILE%
echo import org.springframework.stereotype.Repository;>> %DAO_IMPL_FILE%
echo @Repository>> %DAO_IMPL_FILE%
echo public class %MODEL_CLASS%DaoImpl extends %BASIC_DAO%Impl^<%MODEL_CLASS%, Long^> implements %MODEL_CLASS%Dao {}>> %DAO_IMPL_FILE%

set SERVICE_FILE="%JAVA_FOLDER%\%SERVICE_FOLDER%\%MODEL_CLASS%Service%MODEL_EXTENSION%"
copy /Y nul %SERVICE_FILE%
echo package %BASE_PACKAGE%.%SERVICE_PACKAGE%;>> %SERVICE_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_FILE%
echo public interface %MODEL_CLASS%Service extends %BASIC_SERVICE%^<%MODEL_CLASS%, Long^> {}>> %SERVICE_FILE%

set SERVICE_IMPL_FILE="%JAVA_FOLDER%\%SERVICE_FOLDER%\impl\%MODEL_CLASS%ServiceImpl%MODEL_EXTENSION%"
copy /Y nul %SERVICE_IMPL_FILE%
echo package %BASE_PACKAGE%.%SERVICE_PACKAGE%.impl;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%SERVICE_PACKAGE%.%MODEL_CLASS%Service;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%SERVICE_PACKAGE%.impl.%BASIC_SERVICE%Impl;>> %SERVICE_IMPL_FILE%
echo import org.springframework.stereotype.Service;>> %SERVICE_IMPL_FILE%
echo @Service>> %SERVICE_IMPL_FILE%
echo public class %MODEL_CLASS%ServiceImpl extends %BASIC_SERVICE%Impl^<%MODEL_CLASS%, Long, %MODEL_CLASS%Dao^> implements %MODEL_CLASS%Service {}>> %SERVICE_IMPL_FILE%
REM ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

pause