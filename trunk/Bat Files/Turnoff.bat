@Echo off

Echo Chon 1 option:
Echo  1.Shutdown
Echo  2.Restart
Echo  3.Hibernate
Echo  4.Logoff 

:SELECT_OPTION
SET /P OPTION=Chon 1 so tu 1 den 4:

if [%OPTION%] == [1] (
  Set COMMAND=Shutdown -s
) else if [%OPTION%] == [2] (
  Set COMMAND=Shutdown -r
) else if [%OPTION%] == [3] (
  Set COMMAND=psShutdown -h -c
) else if [%OPTION%] == [4] (
  Set COMMAND=Shutdown -l
) else (
  Echo Ban phai chon 1 so tu 1 den 4.
  Goto SELECT_OPTION
)

if [%OPTION%] == [4] (  
	%COMMAND% -f
)
	SET /P Time=Nhap vap thoi gian (tinh bang giay):
	%COMMAND% -f -t %Time%