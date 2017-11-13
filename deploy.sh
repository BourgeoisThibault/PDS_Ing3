#!/usr/bin/env bash

sshpass -p 'esibankpds' scp StrictHostKeyChecking=no -r -p /builds/esibank-project/project-esibank/notification-server/target/notificationserver.war esibank@192.154.88.151:/home/esibank/app.war