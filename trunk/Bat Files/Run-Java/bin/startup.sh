CLASSPATH=../classes

for i in `ls -a ../lib`;
do
    CLASSPATH=../lib/$i:$CLASSPATH
done

#echo  $CLASSPATH
/opt/java/bin/java -Dapp.config=../conf/AppConfig.properties -cp $CLASSPATH org.demo.MainClass
