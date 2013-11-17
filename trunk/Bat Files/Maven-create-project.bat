@echo off

call Maven-common.bat

SET ARTIFACT_GROUP_ID=org.apache.tapestry
SET ARCHE_TYPE_ARTIFACT_ID=quickstart
::A list of ARCHE_TYPE_ARTIFACT_ID(archetypeArtifactId) is at http://maven.apache.org/archetype/maven-archetype-bundles/index.html

start mvn archetype:create %PRIMARY_OPTIONS% -DarchetypeGroupId=%ARTIFACT_GROUP_ID% -DarchetypeArtifactId=%ARCHE_TYPE_ARTIFACT_ID% -DarchetypeVersion=%ARCHE_TYPE_ARTIFACT_VERSION%