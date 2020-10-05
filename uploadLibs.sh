#!/bin/bash
cd "$(dirname "$0")"
/usr/bin/ssh pi4 rm casino/libs/*
/usr/bin/rsync target/huland-casino-0.0.1-SNAPSHOT/WEB-INF/lib/* pi4:casino/libs
