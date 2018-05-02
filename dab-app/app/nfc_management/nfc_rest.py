#
#@author ABID BUTT Usman
#

import logging
import datetime
import requests

import logging

LOALHOST_DAB_URL = "http://localhost:5000/card_checking"


def send_contact_info(card_id,pin):
    url = LOALHOST_DAB_URL + "?card_id=" + str(card_id)+"&&pin="+str(pin)
    logging.info("###########################################################")
    logging.info("##### Sending URL from nfc_gateway : "+url)
    logging.info("###########################################################")

    response = requests.get(url)

    if response.status_code == 200:
        logging.info("##### DAB accepts information : "+url)
    else:
        logging.error("##### Error sending information to DAB : "+url)
