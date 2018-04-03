#!/bin/bash

# Check if script is execute with SUDO permission
if [ "$UID" -ne "0" ]; then
   echo "Thibaut ! execute en SUDO :D"
   exit 1
fi

# Remove folder
rm -r dab-app

# Create folder
mkdir dab-app

# Enter in folder
cd dab-app

# Download compress dab-app application file
wget http://api.esibank.inside.esiag.info/install_dab_app/dab-app.tgz

# Download Dockerfile
wget http://api.esibank.inside.esiag.info/install_dab_app/Dockerfile

# Stop all container
docker container stop dabappcontainer

# Remove all container
docker container rm dabappcontainer

# Build image and remove older
docker build --rm -t dabappcontainer:1.0 .

# Run container PayFree
docker run --name=dabappcontainer -d -p 5000:5000 dabappcontainer:1.0

echo "#######################";
echo "# Waiting start       #";
echo "#######################";

endtime=$(($(date +%s) + 1000))
boolsuccess=false
while (( $(date +%s) < $endtime )) ; do
  STATUS=$(curl -s -o /dev/null -w '%{http_code}' http://127.0.0.1:5000)
  if [ $STATUS -eq 200 ]; then
    timetoend=$((1000-$(($endtime - $(date +%s)))))
    echo "";
    echo "Successfully deploy in $(($timetoend/60)) minutes and $(($timetoend%60)) seconds"
    boolsuccess=true
    break
  else
    timebeforeend=$(($endtime - $(date +%s)))
    echo -ne "\rWaiting during $(($timebeforeend/60)) minutes and $(($timebeforeend%60)) seconds";
  fi
done

if [ $boolsuccess == false ]; then
  echo ""
  echo "ERROR: Application not completely deploy" >&2
  exit 1
fi
