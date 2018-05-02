#!/bin/bash

export READER_PATH=/home/pi/explore-nfc
export HOME_PATH=/home/pi


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

echo "###############################";
echo "##### DOWNLOADING FILES #######";
echo "###############################";

#Download pip
apt-get install python-pip -y

# Download compress dab-app application file
wget http://api.esibank.inside.esiag.info/install_dab_app/dab-app.tgz

# Extract dab-app
tar -xvf dab-app.tgz

# Download Dockerfile
wget http://api.esibank.inside.esiag.info/install_dab_app/Dockerfile


# Download nef reader C lib
wget http://api.esibank.inside.esiag.info/install_dab_app/nfc-reader-lib.tgz

echo "#######################";
echo "# Manage NFC LIB step  #";
echo "#######################";


echo "tar -xvf nfc-reader-lib.tgz"
tar -xvf nfc-reader-lib.tgz

echo "rm nfc-reader-lib/build"
rm -r nfc-reader-lib/build

echo "mkdir nfc-reader-lib/build"
mkdir nfc-reader-lib/build

echo "rm ~/explore-nfc"
rm -r $HOME_PATH/explore-nfc

echo "mkdir ~/explore-nfc"
mkdir  $HOME_PATH/explore-nfc

echo "cp -r nfc-reader-lib/* ~/explore-nfc"
cp -r nfc-reader-lib/*  $HOME_PATH/explore-nfc

echo "Chemin courant : "`pwd`
cd $HOME_PATH/explore-nfc/build

echo "sed -i -e 's/${READER_PATH}/\/home\/pi\/explore-nfc/g' /home/pi/explore-nfc/source/CMakeCache.txt"
sed -i -e 's/${READER_PATH}/\/home\/pi\/explore-nfc/g' /home/pi/explore-nfc/source/CMakeCache.txt

echo "cp ../source/Makefile ./"
cp ../source/Makefile ./

sed -i -e 's/${READER_PATH}//home/pi/explore/g' ~/explore-nfc/source/CMakeCache.txt

echo "cmake../source"
cmake ../source

echo "make"
echo "Chemin courant"`pwd`
cd $HOME_PATH/explore-nfc/source
make

echo "chmod 777 ./cardEmulation-fb"
chmod 777 ./cardEmulation-fb

echo "./cardEmulation-fb"
./cardEmulation-fb &


echo "###############################";
echo "# Docker step for REDIS IMAGE #";
echo "###############################";

# Stop all container
docker container stop redis

# Remove all container
docker container rm redis


cd $HOME_PATH/
docker pull redis
docker run -d --name redis -p 6379:6379 --restart unless-stopped redis


echo "#######################################";
echo "#### Docker step for DABAPP IMAGE #####";
echo "#######################################";

echo "cd $HOME_PATH/dab-app/dab-app"
cd $HOME_PATH/dab-app/dab-app

echo "pip install -r requirements.txt"
pip install -r requirements.txt

echo "python application.py"
python application.py

#echo "$HOME_PATH/dab-app/"
#cd $HOME_PATH/dab-app/

# Stop all container
#docker container stop dabappcontainer

# Remove all container
#docker container rm dabappcontainer

# Build image and remove older
#docker build --rm -t dabappcontainer:1.0 .

# Run container dab-app
#docker run --name=dabappcontainer -d -p 5000:5000 --restart unless-stopped dabappcontainer:1.0

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
