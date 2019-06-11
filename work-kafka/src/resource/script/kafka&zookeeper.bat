start D:\程序包\zookeeper-3.4.10\bin\zkServer.cmd
timeout /t 30
cd D:\程序包\kafka_2.12-2.2.0\bin\windows\
start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka\src\resource\kafka-properties\server1.properties
start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka\src\resource\kafka-properties\server2.properties
rem start kafka-server-start.bat D:\coding\java\ssm-git-learn\work-kafka\src\resource\kafka-properties\server3.properties
cd D:\程序包\ZooInspector\build\
start java -jar zookeeper-dev-ZooInspector.jar
cd D:\coding\java\ssm-git-learn\work-kafka\src\resource\script
