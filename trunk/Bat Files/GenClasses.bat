@echo off

REM -------------------------------------------------------------------------
set OUTPUT_FOLDER=d:\Data-Store\Projects\tapestry5-demo\module-struts2
::rd /S /Q %OUTPUT_FOLDER%

set BASE_PACKAGE=org.java.demo
set MODEL_CLASS=Employee
REM --------------------------------------------------------------------------

REM ==========================================================================
set NUMERIC_ID_TYPE=Long
set MODEL_PACKAGE=model
set DAO_PACKAGE=dao
set SERVICE_PACKAGE=service
set BASIC_MODEL=NumericIdEntity
set BASIC_SERVICE=BasicService
set BASIC_DAO=BasicDao
set ABSTRACT_DAO=AbstractDao
set ABSTRACT_SERVICE=AbstractService
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
if not exist "%JAVA_FOLDER%\%DAO_FOLDER%" (
	mkdir "%JAVA_FOLDER%\%DAO_FOLDER%"	
)
if not exist "%JAVA_FOLDER%\%SERVICE_FOLDER%" (
	mkdir "%JAVA_FOLDER%\%SERVICE_FOLDER%"
)

set MODEL_FILE="%JAVA_FOLDER%\%MODEL_FOLDER%\%MODEL_CLASS%%MODEL_EXTENSION%"
copy /Y nul %MODEL_FILE%
echo package %BASE_PACKAGE%.%MODEL_PACKAGE%;>> %MODEL_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.core.%BASIC_MODEL%;>> %MODEL_FILE%
echo public class %MODEL_CLASS% extends %BASIC_MODEL%{}>> %MODEL_FILE%

set DAO_FILE="%JAVA_FOLDER%\%DAO_FOLDER%\%MODEL_CLASS%Dao%MODEL_EXTENSION%"
copy /Y nul %DAO_FILE%
echo package %BASE_PACKAGE%.%DAO_PACKAGE%;>> %DAO_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.core.%BASIC_DAO%;>> %DAO_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_FILE%
echo public interface %MODEL_CLASS%Dao extends %BASIC_DAO%^<%MODEL_CLASS%, %NUMERIC_ID_TYPE%^> {}>> %DAO_FILE%

set DAO_IMPL_FILE="%JAVA_FOLDER%\%DAO_FOLDER%\%MODEL_CLASS%DaoImpl%MODEL_EXTENSION%"
copy /Y nul %DAO_IMPL_FILE%
echo package %BASE_PACKAGE%.%DAO_PACKAGE%;>> %DAO_IMPL_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_IMPL_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %DAO_IMPL_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.core.%ABSTRACT_DAO%;>> %DAO_IMPL_FILE%
echo import org.springframework.stereotype.Repository;>> %DAO_IMPL_FILE%
echo @Repository>> %DAO_IMPL_FILE%
echo public class %MODEL_CLASS%DaoImpl extends %ABSTRACT_DAO%^<%MODEL_CLASS%, %NUMERIC_ID_TYPE%^> implements %MODEL_CLASS%Dao {}>> %DAO_IMPL_FILE%

set SERVICE_FILE="%JAVA_FOLDER%\%SERVICE_FOLDER%\%MODEL_CLASS%Service%MODEL_EXTENSION%"
copy /Y nul %SERVICE_FILE%
echo package %BASE_PACKAGE%.%SERVICE_PACKAGE%;>> %SERVICE_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_FILE%
echo import %BASE_PACKAGE%.%SERVICE_PACKAGE%.core.%BASIC_SERVICE%;>> %SERVICE_FILE%
echo public interface %MODEL_CLASS%Service extends %BASIC_SERVICE%^<%MODEL_CLASS%, %NUMERIC_ID_TYPE%^> {}>> %SERVICE_FILE%

set SERVICE_IMPL_FILE="%JAVA_FOLDER%\%SERVICE_FOLDER%\%MODEL_CLASS%ServiceImpl%MODEL_EXTENSION%"
copy /Y nul %SERVICE_IMPL_FILE%
echo package %BASE_PACKAGE%.%SERVICE_PACKAGE%;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%SERVICE_PACKAGE%.%MODEL_CLASS%Service;>> %SERVICE_IMPL_FILE%
echo import %BASE_PACKAGE%.%SERVICE_PACKAGE%.core.%ABSTRACT_SERVICE%;>> %SERVICE_IMPL_FILE%
echo import org.springframework.stereotype.Service;>> %SERVICE_IMPL_FILE%
echo @Service>> %SERVICE_IMPL_FILE%
echo public class %MODEL_CLASS%ServiceImpl extends %ABSTRACT_SERVICE%^<%MODEL_CLASS%, %NUMERIC_ID_TYPE%, %MODEL_CLASS%Dao^> implements %MODEL_CLASS%Service {}>> %SERVICE_IMPL_FILE%
REM ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

pause