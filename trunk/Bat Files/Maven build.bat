@Echo off

set PROJECT_HOME="D:\Long\LATN\Source Code\e-mobileshop"
set TOMCAT_HOME="D:\Long\LATN\Software\Apache Tomcat latn"

IF EXIST %TOMCAT_HOME%\bin (
  cd /d %TOMCAT_HOME%\bin

  Echo SHUTDOWN TOMCAT
  REM call shutdown.bat
) ELSE (
  Echo TOMCAT does not exist.
)

cd /d %PROJECT_HOME%

start mvn clean install -Dmaven.test.skip=true