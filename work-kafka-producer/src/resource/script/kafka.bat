rem timeout /t 30
cd D:\程序包\kafka_2.12-2.2.0\bin\windows\
timeout /t 5
start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka-producer\src\resource\kafka-properties\server1.properties
timeout /t 5
start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka-producer\src\resource\kafka-properties\server2.properties
timeout /t 5
start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka-producer\src\resource\kafka-properties\server3.properties
cd D:\程序包\ZooInspector\build\
start java -jar zookeeper-dev-ZooInspector.jar
cd D:\coding\java\ssm-git-learn\work-kafka\src\resource\script
