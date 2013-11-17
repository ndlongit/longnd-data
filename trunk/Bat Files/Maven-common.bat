@echo off

SET GROUP_ID=com.mycompany
SET ARTIFACT_ID=my-webapp
SET ARCHE_TYPE_VERSION=RELEASE
SET PACKAGE_NAME=com.mycompany.pk1

::del /F /Q /S %ARTIFACT_ID%

SET PRIMARY_OPTIONS=-DgroupId=%GROUP_ID% -DartifactId=%ARTIFACT_ID% -DarchetypeVersion=%ARCHE_TYPE_VERSION% -DpackageName=%PACKAGE_NAME%