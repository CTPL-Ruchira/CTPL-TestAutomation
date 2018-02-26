cd /NetConnect-Test-Framework
call mvn clean install -f pom.xml
cd ../NetConnect-NetChain2
call mvn clean install -f pom.xml
call mvn exec:java -f pom.xml

