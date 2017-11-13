#!/usr/bin/env bash

scp StrictHostKeyChecking=no -r -p esibank@192.154.88.151:/builds/esibank-project/project-esibank/notification-server/target/notificationserver.war esibank@192.154.88.151:/home/esibank/app.war