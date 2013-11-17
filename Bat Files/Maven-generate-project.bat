@echo off

call Maven-common.bat

SET ARCHE_TYPE_CATALOG=http://tapestry.apache.org

start mvn archetype:generate %PRIMARY_OPTIONS% -DarchetypeCatalog=%ARCHE_TYPE_CATALOG%