cd /NetConnect-Test-Framework
sh mvn clean install -f pom.xml
cd ../NetConnect-NetChain2
sh mvn clean install -f pom.xml
sh mvn exec:java -f pom.xml

