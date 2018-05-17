#
#@author ABID BUTT Usman
#
from datetime import datetime
import requests
import logging
import json
from elasticsearch import Elasticsearch

from app import load_properties

_ELASTICSEARCH_URL = load_properties.get_properties("elastic_url")
_ELASTICSEARCH_PORT = load_properties.get_properties("elastic_port")

_CHECK_CARD_URL = load_properties.get_properties("check_url")
_VALID_CARD_URL = load_properties.get_properties("valid_url")
_CONFIRME_CARD_URL = load_properties.get_properties("confirme_url")

es = Elasticsearch(
    [_ELASTICSEARCH_URL],
    scheme="http",
    port=_ELASTICSEARCH_PORT
)


def check_valid_card(card_id, pin):
    url = _CHECK_CARD_URL+"?card="+str(card_id)+"&&pin="+str(pin)
    logging.info("###########################################################")
    logging.info("##### Check valid card request : "+url)
    logging.info("###########################################################")

    try:
        response = requests.get(url)
    except requests.exceptions.RequestException as e:
        logging.info("PAYFREE CHECK", "ERROR COMMUNICATION WITH PAYFREE MODULE : ")
        return None
    if response.status_code == 200:
        data = get_elastic_check_log("Contact", 200, True, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        return True
    elif response.status_code == 401:
        data = get_elastic_check_log("Contact", 401, False, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        return False
    else:
        data = get_elastic_check_log("Contact", 401, False, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        return None


def check_valid_transac(card_id, pin, amount):

    url = _VALID_CARD_URL+"?card="+str(card_id)+"&&pin="+str(pin)+"&&amount="+str(amount)
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

    url = _CONFIRME_CARD_URL+"?card="+str(card_id)+"&&pin="+str(pin)+"&&amount="+str(amount)
    logging.info("###########################################################")
    logging.info("##### Check confirm transac request : "+url)
    logging.info("###########################################################")

    try:
        response = requests.get(url)
    except requests.exceptions.RequestException as e:
        logging.info("PAYFREE VALIDATE", "ERROR COMMUNICATION WITH PAYFREE MODULE : ")
        return None

    if response.status_code == 200:
        data = get_elastic_check_log("Contact", 200, True, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        data = get_elastic_confirme_log("Retrait", 200, True, amount, card_id)
        res = es.index(index="dab-confirm-nfc", doc_type='log', body=data)
        return True
    elif response.status_code == 401:
        data = get_elastic_check_log("Contact", 401, False, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        data = get_elastic_confirme_log("Retrait", 401, False, amount, card_id)
        res = es.index(index="dab-confirm-nfc", doc_type='log', body=data)
        return False
    else:
        data = get_elastic_check_log("Contact", 401, False, card_id)
        res = es.index(index="dab-contact-nfc", doc_type='log', body=data)
        data = get_elastic_confirme_log("Retrait", 400, False, amount, card_id)
        res = es.index(index="dab-confirm-nfc", doc_type='log', body=data)
        return None


def get_elastic_check_log(message,code, validBool, cardid):
    data = {
        "message": message+" sur DAB",
        "card_id": cardid,
        "code_status": code,
        "valid_card": validBool,
        "date": datetime.now()
    }
    return data


def get_elastic_confirme_log(message,code,validBool, amount, cardid):
    data = {
        "message": message+" sur DAB",
        "card_id":cardid,
        "code_status": code,
        "valid_card": validBool,
        "amount":amount,
        "date": datetime.now()
    }
    return data
