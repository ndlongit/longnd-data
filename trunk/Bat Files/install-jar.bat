set GROUP_ID=gui.ava
set ARTIFACT_ID=html2image
set VERSION=0.9
set FILE=html2image-0.9.jar
mvn install:install-file -DgroupId=%GROUP_ID% -DartifactId=%ARTIFACT_ID% -Dversion=%VERSION% -Dpackaging=jar -Dfile=%FILE%