import requests
import logging
import json
PATH = "card_file.json"


def getAccountsList(card_id):
    logging.info("APPEL method rest getAccountsList")
    response = requests.get("http://ws.esibank.inside.esiag.info/checkAccountValid/"+str(card_id))
    print("Message recu : "+str(response.text))
    remove_card()
    return response.text

def updateAccount(card_id, amount):
    return True


def check_valid_card(card_id,pin):
    # TODO Add resquest to PayFREE
    return True


def check_valid_transac(card_id,pin,amount):
    # TODO Add resquest to PayFREE with amount
    return True


def remove_card():
    #FILE["server1"] = 0
    #FILE["server2"] = 0
    print("Removing card...")
    FILE = [{"nfc1": 0, "nfc2": 0}]
    with open(PATH, "w") as jsonFile:
        json.dump(FILE, jsonFile)
    print("Remove card : OK")


