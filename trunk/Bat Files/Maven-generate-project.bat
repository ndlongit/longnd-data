@echo off

call Maven-common.bat

SET archetypeCatalog=http://repo1.maven.org/maven2/archetype-catalog.xml
SET archetypeCatalog=http://tapestry.apache.org

start mvn archetype:generate %PRIMARY_OPTIONS% -DarchetypeCatalog=%archetypeCatalog%