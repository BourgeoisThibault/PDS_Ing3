#
#@author ABID BUTT Usman
#
from datetime import datetime
import requests
import logging
import json
from elasticsearch import Elasticsearch
es = Elasticsearch(
    ['elk.esibank.inside.esiag.info'],
    scheme="http",
    port=9200
)


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
        data = {
            "message": "Contact sur DAB reussi",
            "code_status": 200,
            "valid_card": True,
            "date": datetime.now()
                }
        res = es.index(index="dab-contact-nfc", doc_type='log', id=1, body=data)
        # requests.post("http://elk.esibank.inside.esiag.info:9200/dab-contact-nfc/log", data=data)
        return True
    elif response.status_code == 401:
        data = {
            "message": "Contact sur DAB non autorise",
            "code_status": 401,
            "valid_card": False,
            "date": datetime.now()
        }
        requests.post("http://elk.esibank.inside.esiag.info:9200/dab-contact-nfc/log", data=data)
        return False
    else:
        data = {
            "message": "Contact sur DAB erreur DAB",
            "code_status": 0,
            "valid_card": False,
            "date": datetime.now()
        }
        requests.post("http://elk.esibank.inside.esiag.info:9200/dab-contact-nfc/log", data=data)
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
