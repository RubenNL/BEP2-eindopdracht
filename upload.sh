#!/bin/bash
cd "$(dirname "$0")"
/usr/bin/rsync -a --exclude WEB-INF --exclude META-INF src/main/webapp/* pi4:casino/website
