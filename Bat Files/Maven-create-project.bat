@echo off

SET GROUP_ID=com.mycompany.app
SET ARTIFACT_ID=my-webapp
SET ARCHE_TYPE_ARTIFACT_ID=maven-archetype-webapp

::del /F /Q /S %ARTIFACT_ID%

start mvn archetype:create -DgroupId=%GROUP_ID% -DartifactId=%ARTIFACT_ID% -DarchetypeArtifactId=%ARCHE_TYPE_ARTIFACT_ID%