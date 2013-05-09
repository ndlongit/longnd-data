@echo off
REM --------------------------------------------------------------------------
::	Version: 1.0
REM --------------------------------------------------------------------------

REM --------------------------------------------------------------------------
set OUTPUT_FOLDER=D:\Others\java-classes
set MODEL_CLASS=Payment
REM --------------------------------------------------------------------------

REM ==========================================================================
set BASE_PACKAGE=com.structis.fichesst
set MODEL_PACKAGE=bean.domain
set DAO_PACKAGE=dao
set SERVICE_PACKAGE=service.domain
set BASIC_MODEL=BasicEntity
set BASIC_SERVICE=BasicService
set BASIC_DAO=BasicDao
set DTO_PACKAGE=dto
set BASIC_DTO=AbstractDto
REM ==========================================================================

REM ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set CLIENT_PACKAGE=%BASE_PACKAGE%.client
set SERVER_PACKAGE=%BASE_PACKAGE%.server
set SHARED_PACKAGE=%BASE_PACKAGE%.shared

set CLIENT_FOLDER=
for /F "delims=. tokens=1,2,3,4*" %%i in ("%CLIENT_PACKAGE%") do (
	set CLIENT_FOLDER=%%i\%%j\%%k\%%l
)

set SERVER_FOLDER=
for /F "delims=. tokens=1,2,3,4*" %%i in ("%SERVER_PACKAGE%") do (
	set SERVER_FOLDER=%%i\%%j\%%k\%%l
)

set SHARED_FOLDER=
for /F "delims=. tokens=1,2,3,4*" %%i in ("%SHARED_PACKAGE%") do (
	set SHARED_FOLDER=%%i\%%j\%%k\%%l
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

set DTO_FOLDER=
for /F "delims=. tokens=1*" %%i in ("%DTO_PACKAGE%") do (
	set DTO_FOLDER=%%i
)

set CLIENT_JAVA_FOLDER=%OUTPUT_FOLDER%\src\main\java\%CLIENT_FOLDER%
set SERVER_JAVA_FOLDER=%OUTPUT_FOLDER%\src\main\java\%SERVER_FOLDER%
set SHARED_JAVA_FOLDER=%OUTPUT_FOLDER%\src\main\java\%SHARED_FOLDER%

if not exist "%SERVER_JAVA_FOLDER%/service/client" (
	mkdir "%SERVER_JAVA_FOLDER%/service/client"
)
if not exist "%CLIENT_JAVA_FOLDER%/service" (
	mkdir "%CLIENT_JAVA_FOLDER%/service"	
)
if not exist "%SERVER_JAVA_FOLDER%\%MODEL_FOLDER%" (
	mkdir "%SERVER_JAVA_FOLDER%\%MODEL_FOLDER%"	
)
if not exist "%SERVER_JAVA_FOLDER%\%DAO_FOLDER%" (
	mkdir "%SERVER_JAVA_FOLDER%\%DAO_FOLDER%"	
)
if not exist "%SERVER_JAVA_FOLDER%\%SERVICE_FOLDER%" (
	mkdir "%SERVER_JAVA_FOLDER%\%SERVICE_FOLDER%"
)
if not exist "%SHARED_JAVA_FOLDER%\%DTO_FOLDER%" (
	mkdir "%SHARED_JAVA_FOLDER%\%DTO_FOLDER%"
)

set CLIENT_SERVICE_FILE="%CLIENT_JAVA_FOLDER%/service/Client%MODEL_CLASS%%Service.java"
copy /Y nul %CLIENT_SERVICE_FILE%
echo package %CLIENT_PACKAGE%.service;>> %CLIENT_SERVICE_FILE%
echo import com.google.gwt.user.client.rpc.RemoteService;>> %CLIENT_SERVICE_FILE%
echo import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;>> %CLIENT_SERVICE_FILE%
echo @RemoteServiceRelativePath("springGwtServices/client%MODEL_CLASS%Service")>> %CLIENT_SERVICE_FILE%
echo public interface Client%MODEL_CLASS%Service extends RemoteService {}>> %CLIENT_SERVICE_FILE%

set CLIENT_SERVICE_ASYNC_FILE="%CLIENT_JAVA_FOLDER%/service/Client%MODEL_CLASS%ServiceAsync%.java"
copy /Y nul %CLIENT_SERVICE_ASYNC_FILE%
echo package %CLIENT_PACKAGE%.service;>> %CLIENT_SERVICE_ASYNC_FILE%
echo import com.google.gwt.core.client.GWT;>> %CLIENT_SERVICE_ASYNC_FILE%
echo public interface Client%MODEL_CLASS%ServiceAsync {>> %CLIENT_SERVICE_ASYNC_FILE%
echo public static class Util {>> %CLIENT_SERVICE_ASYNC_FILE%
echo private static Client%MODEL_CLASS%ServiceAsync instance = GWT.create(Client%MODEL_CLASS%Service.class);>> %CLIENT_SERVICE_ASYNC_FILE%
echo public static Client%MODEL_CLASS%ServiceAsync getInstance() {>> %CLIENT_SERVICE_ASYNC_FILE%
echo return instance;}}}>> %CLIENT_SERVICE_ASYNC_FILE%

set CLIENT_SERVICE_IMPL_FILE="%SERVER_JAVA_FOLDER%/service/client/Client%MODEL_CLASS%ServiceImpl%.java"
copy /Y nul %CLIENT_SERVICE_IMPL_FILE%
echo package %SERVER_PACKAGE%.service.client;>> %CLIENT_SERVICE_IMPL_FILE%
echo import org.springframework.beans.factory.annotation.Autowired;>> %CLIENT_SERVICE_IMPL_FILE%
echo import org.springframework.stereotype.Service;>> %CLIENT_SERVICE_IMPL_FILE%
echo import %CLIENT_PACKAGE%.service.Client%MODEL_CLASS%Service;>> %CLIENT_SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.core.DependencyInjectionRemoteServiceServlet;>> %CLIENT_SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.%SERVICE_PACKAGE%.%MODEL_CLASS%Service;>> %CLIENT_SERVICE_IMPL_FILE%
echo @Service("client%MODEL_CLASS%Service")>> %CLIENT_SERVICE_IMPL_FILE%
echo public class Client%MODEL_CLASS%ServiceImpl extends DependencyInjectionRemoteServiceServlet implements Client%MODEL_CLASS%Service {>> %CLIENT_SERVICE_IMPL_FILE%
echo @Autowired	private %MODEL_CLASS%Service /*%MODEL_CLASS%Service*/;}>> %CLIENT_SERVICE_IMPL_FILE%

set DTO_FILE="%SHARED_JAVA_FOLDER%/%DTO_FOLDER%/%MODEL_CLASS%%Dto.java"
copy /Y nul %DTO_FILE%
echo package %SHARED_PACKAGE%.%DTO_PACKAGE%;>> %DTO_FILE%
echo public class %MODEL_CLASS%Dto extends %BASIC_DTO% {}>> %DTO_FILE%

set MODEL_FILE="%SERVER_JAVA_FOLDER%\%MODEL_FOLDER%\%MODEL_CLASS%%.java"
copy /Y nul %MODEL_FILE%
echo package %SERVER_PACKAGE%.%MODEL_PACKAGE%;>> %MODEL_FILE%

echo import javax.persistence.AttributeOverride;>> %MODEL_FILE%
echo import javax.persistence.Column;>> %MODEL_FILE%
echo import javax.persistence.Entity;>> %MODEL_FILE%
echo import javax.persistence.Table;>> %MODEL_FILE%
echo import %SERVER_PACKAGE%.%MODEL_PACKAGE%.core.NumericIdEntity;>> %MODEL_FILE%
echo @Entity>> %MODEL_FILE%
echo @Table(name="%MODEL_CLASS%")>> %MODEL_FILE%
echo @AttributeOverride(name = "id", column = @Column(name = "ID_%MODEL_CLASS%"))>> %MODEL_FILE%>> %MODEL_FILE%
echo public class %MODEL_CLASS% extends NumericIdEntity {}>> %MODEL_FILE%

set DAO_FILE="%SERVER_JAVA_FOLDER%\%DAO_FOLDER%\%MODEL_CLASS%Dao%.java"
copy /Y nul %DAO_FILE%
echo package %SERVER_PACKAGE%.%DAO_PACKAGE%;>> %DAO_FILE%
echo import %SERVER_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_FILE%
echo public interface %MODEL_CLASS%Dao extends %BASIC_DAO%^<%MODEL_CLASS%, Integer^> {}>> %DAO_FILE%

set DAO_IMPL_FILE="%SERVER_JAVA_FOLDER%\%DAO_FOLDER%\%MODEL_CLASS%DaoImpl%.java"
copy /Y nul %DAO_IMPL_FILE%
echo package %SERVER_PACKAGE%.%DAO_PACKAGE%;>> %DAO_IMPL_FILE%
echo import %SERVER_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %DAO_IMPL_FILE%
echo import %SERVER_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %DAO_IMPL_FILE%
echo import org.springframework.stereotype.Repository;>> %DAO_IMPL_FILE%
echo @Repository>> %DAO_IMPL_FILE%
echo public class %MODEL_CLASS%DaoImpl extends %BASIC_DAO%Impl^<%MODEL_CLASS%, Integer^> implements %MODEL_CLASS%Dao {}>> %DAO_IMPL_FILE%

set SERVICE_FILE="%SERVER_JAVA_FOLDER%\%SERVICE_FOLDER%\%MODEL_CLASS%Service%.java"
copy /Y nul %SERVICE_FILE%
echo package %SERVER_PACKAGE%.%SERVICE_PACKAGE%;>> %SERVICE_FILE%
echo import %SERVER_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_FILE%
echo public interface %MODEL_CLASS%Service extends %BASIC_SERVICE%^<%MODEL_CLASS%, Integer^> {}>> %SERVICE_FILE%

set SERVICE_IMPL_FILE="%SERVER_JAVA_FOLDER%\%SERVICE_FOLDER%\%MODEL_CLASS%ServiceImpl%.java"
copy /Y nul %SERVICE_IMPL_FILE%
echo package %SERVER_PACKAGE%.%SERVICE_PACKAGE%;>> %SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.%MODEL_PACKAGE%.%MODEL_CLASS%;>> %SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.%DAO_PACKAGE%.%MODEL_CLASS%Dao;>> %SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.%SERVICE_PACKAGE%.%MODEL_CLASS%Service;>> %SERVICE_IMPL_FILE%
echo import %SERVER_PACKAGE%.%SERVICE_PACKAGE%.%BASIC_SERVICE%Impl;>> %SERVICE_IMPL_FILE%
echo import org.springframework.stereotype.Service;>> %SERVICE_IMPL_FILE%
echo @Service>> %SERVICE_IMPL_FILE%
echo public class %MODEL_CLASS%ServiceImpl extends %BASIC_SERVICE%Impl^<%MODEL_CLASS%, Integer, %MODEL_CLASS%Dao^> implements %MODEL_CLASS%Service {}>> %SERVICE_IMPL_FILE%
REM ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

pause