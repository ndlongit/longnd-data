@echo off

call Maven-common.bat

start mvn archetype:create -DgroupId=%GROUP_ID% -DartifactId=%ARTIFACT_ID% -DarchetypeArtifactId=%ARCHE_TYPE_ARTIFACT_ID%