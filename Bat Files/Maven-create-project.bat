@echo off

call Maven-common.bat

SET ARTIFACT_GROUP_ID=org.telosys.starterkits
SET ARCHE_TYPE_ARTIFACT_VERSION=1.0.0
SET ARCHE_TYPE_ARTIFACT_ID=struts-jpa-starterkit
::A list of ARCHE_TYPE_ARTIFACT_ID(archetypeArtifactId) is at http://maven.apache.org/archetype/maven-archetype-bundles/index.html

start mvn archetype:create %PRIMARY_OPTIONS% -DarchetypeGroupId=%ARTIFACT_GROUP_ID% -DarchetypeArtifactId=%ARCHE_TYPE_ARTIFACT_ID% -DarchetypeVersion=%ARCHE_TYPE_ARTIFACT_VERSION%