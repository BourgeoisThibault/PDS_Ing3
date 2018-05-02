#
#@author ABID BUTT Usman
#

import requests
import logging
import json


def check_valid_card(card_id, pin):

    url = "http://localhost:4321/check?card="+str(card_id)+"&&pin="+str(pin)
    logging.info("###########################################################")
    logging.info("##### Check valid card request : "+url)
    logging.info("###########################################################")

    try:
        response = requests.get(url)
    except requests.exceptions.RequestException as e:
        logging.info("PAYFREE CHECK", "ERROR COMMUNICATION WITH PAYFREE MODULE : ")
        return None

    if response.status_code == 200:
        return True
    elif response.status_code == 401:
        return False
    else:
        return None


def check_valid_transac(card_id, pin, amount):

    url = "http://localhost:4321/validate?card="+str(card_id)+"&&pin="+str(pin)+"&&amount="+str(amount)
    logging.info("###########################################################")
    logging.info("##### Check valid transac request : "+url)
    logging.info("###########################################################")

    try:
        response = requests.get(url)
    except requests.exceptions.RequestException as e:
        logging.info("PAYFREE VALIDATE", "ERROR COMMUNICATION WITH PAYFREE MODULE : ")
        return None

    if response.status_code == 200:
        return True
    elif response.status_code == 401:
        return False
    else:
        return None


def check_confirm_transac(card_id, pin, amount):

    url = "http://localhost:4321/confirme?card="+str(card_id)+"&&pin="+str(pin)+"&&amount="+str(amount)
    logging.info("###########################################################")
    logging.info("##### Check confirm transac request : "+url)
    logging.info("###########################################################")

    try:
        response = requests.get(url)
    except requests.exceptions.RequestException as e:
        logging.info("PAYFREE VALIDATE", "ERROR COMMUNICATION WITH PAYFREE MODULE : ")
        return None

    if response.status_code == 200:
        return True
    elif response.status_code == 401:
        return False
    else:
        return None
