cd /var/lib/jenkins/workspace/Run_Selenium_NetChain2/NetConnect-Test-Framework
sh mvn clean install -f pom.xml
cd /var/lib/jenkins/workspace/Run_Selenium_NetChain2/NetConnect-NetChain2
sh mvn clean install -f pom.xml
sh mvn exec:java -f pom.xml

