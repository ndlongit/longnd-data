@echo off

set CONNECTION_NAME="Local Area Connection"
set IP_ADDRESS=192.168.20.162
set SUBNET_MASK=255.255.255.0
set DEFAULT_GATEWAY=192.168.20.254

set IP_INFO=static %IP_ADDRESS% %SUBNET_MASK% %DEFAULT_GATEWAY% 1
set IP_INFO=DHCP

::IP Address
netsh interface ip set address name=%CONNECTION_NAME% %IP_INFO%
::netsh interface ip set wins %CONNECTION_NAME% DHCP

::DNS

::Static DNS
netsh interface ip set dns %CONNECTION_NAME% static 192.168.0.100
netsh interface ip add dns %CONNECTION_NAME% 192.168.0.200

::Dynamic DNS
netsh interface ip set dns %CONNECTION_NAME% DHCP

pause
exit