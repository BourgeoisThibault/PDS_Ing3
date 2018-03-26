#!/bin/bash

# Check if script is execute with SUDO permission
if [ "$UID" -ne "0" ]; then
   echo "Usman ! execute en SUDO :D"
   exit 1
fi

# Remove folder
rm -r payfree_client

# Create folder
mkdir payfree_client

# Enter in folder
cd payfree_client

# Download jar
wget http://api.esibank.inside.esiag.info/install/payfreeclt.jar

# Download Dockerfile
wget http://api.esibank.inside.esiag.info/install/Dockerfile

# Stop all container
docker container stop payfreecontainer

# Remove all container
docker container rm payfreecontainer

# Build image and remove older
docker build --rm -t clientpayfree:1.0 .

# Run container PayFree
docker run --name=payfreecontainer -d -p 8080:4321 clientpayfree:1.0

echo "#######################";
echo "# Waiting start       #";
echo "#######################";
sleep 1

endtime=$((now + 1000))
boolsuccess=false
while (( $(date +%s) < $endtime )) ; do
  STATUS=$(curl -s -o /dev/null -w '%{http_code}' http://127.0.0.1:8080)
  if [ $STATUS -eq 200 ]; then
    timetoend=$((1000-$(($endtime - $(date +%s)))))
    echo "";
    echo "Successfully deploy in $timetoend secondes"
    boolsuccess=true
    break
  else
    echo -ne "\r$(($endtime - $(date +%s))) secondes before error";
  fi
done

if [ $boolsuccess == false ]; then
  echo ""
  echo "ERROR: Application not completely deploy (1000 seconds)" >&2
  exit 1
fi
