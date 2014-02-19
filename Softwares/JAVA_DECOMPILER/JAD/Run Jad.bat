::@echo off

set JAD_HOME="E:\Plug-in\JAVA_DECOMPILER"
set DEC_FILES=target/*.class

::cd /d %JAD_HOME%

jad -o -r -s java -d jad-out %DEC_FILES%

pause

