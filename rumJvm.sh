#!/bin/bash

JAVA_OPTS="-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5188 -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true -XX:PermSize=32M -XX:MaxPermSize=32M -Xms64M -Xmx64M  -XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=2 -XX:SurvivorRatio=2 -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:CMSInitiatingOccupancyFraction=80 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseCMSInitiatingOccupancyOnly -XX:+PrintHeapAtGC -Xloggc:./logs/gc.log -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -Duser.timezone=Asia/Shanghai"

java $JAVA_OPTS -jar /Users/liujiaming/Documents/code-open/einstein-experiment/experiment/target/experiment-1.0.0-SNAPSHOT.jar 1000000
