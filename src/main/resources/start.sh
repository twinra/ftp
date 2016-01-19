#!/usr/bin/env bash
java -cp "conf/:lib/*" com.github.twinra.ftpserver.Main -Dlogback.configurationFile=conf/logback.xml &