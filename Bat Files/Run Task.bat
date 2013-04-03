@echo off

set TASK_NAME="CMS Build"

SCHTASKS /Run /TN %TASK_NAME%