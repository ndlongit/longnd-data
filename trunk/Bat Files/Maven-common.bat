@echo off

SET GROUP_ID=com.mycompany.app
SET ARTIFACT_ID=my-webapp
SET ARCHE_TYPE_ARTIFACT_ID=maven-archetype-webapp
::Full list of ARCHE_TYPE_ARTIFACT_ID(archetypeArtifactId) is at http://maven.apache.org/archetype/maven-archetype-bundles/index.html

::del /F /Q /S %ARTIFACT_ID%