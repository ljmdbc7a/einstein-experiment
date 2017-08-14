#!/bin/bash

param=$1

JAVA_OPTS="-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5188 \
	-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true -XX:PermSize=64M -XX:MaxPermSize=64M -Xms64M -Xmx64M\
	-XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=2 -XX:SurvivorRatio=2 -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:ParallelGCThreads=4\
	-XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled\
	-XX:CMSInitiatingOccupancyFraction=80 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseCMSInitiatingOccupancyOnly\
	-XX:+PrintHeapAtGC -Xloggc:./logs/gc.log -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -Duser.timezone=Asia/Shanghai"

#JAVA_BIN="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin/"
#JAVA_BIN="/Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home/bin/"
JAVA_BIN="/Library/Java/JavaVirtualMachines/jdk1.8.0_141.jdk/Contents/Home/bin/"

$JAVA_BIN/java $JAVA_OPTS -jar /Users/lijiamin/Documents/opensource/einstein-experiment/experiment/target/experiment-1.0.0-SNAPSHOT.jar $param
