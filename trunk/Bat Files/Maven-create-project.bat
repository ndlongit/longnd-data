@echo off

call Maven-common.bat

start mvn archetype:create %PRIMARY_OPTIONS% -DarchetypeArtifactId=%ARCHE_TYPE_ARTIFACT_ID%