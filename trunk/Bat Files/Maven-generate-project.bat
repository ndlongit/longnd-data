@echo off

call Maven-common.bat

SET ARCHE_TYPE_CATALOG=http://tapestry.apache.org
SET ARCHE_TYPE_VERSION=5.3.7

start mvn archetype:generate %PRIMARY_OPTIONS% -DarchetypeCatalog=%ARCHE_TYPE_CATALOG% -DarchetypeVersion=%ARCHE_TYPE_VERSION%