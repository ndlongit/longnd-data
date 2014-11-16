@echo off

wmic /namespace:\\root\cimv2 path win32_product where "name like '%%.NET%%'" get version

pause