::Memory size
set JAVA_OPTS=%JAVA_OPTS% -Xms512m -Xmx512m -XX:MaxPermSize=512m

::Remote debug
set JAVA_OPTS=%JAVA_OPTS% -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
