:: Remember to delete classes and lib folders before building
start mvn clean install -Dmaven.test.skip=true
:: -P prod,mysql