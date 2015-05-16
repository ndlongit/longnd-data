::@echo off

set FILE=d:\Virtual PC\Win7-X64\Win7-X64_2.vhd
set VBOX_HOME=C:\Program Files\Oracle\VirtualBox

"%VBOX_HOME%/VBoxManage" internalcommands sethduuid "%FILE%"
pause