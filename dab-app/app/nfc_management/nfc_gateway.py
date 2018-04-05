#
#@author ABID BUTT Usman
#

import sys
import nfc_rest
import logging

CARD_NUMBER_SIZE = 16
CARD_PIN_SIZE = 4


def handle_arg_error():
    if len(sys.argv) == 1:
        logging.error("Erreur, contact NFC...")
        return False
    else:
        logging.info("########################################")
        logging.info("Contact NFC, catch information from device  : " + sys.argv[1])
        logging.info("########################################")
        return True


def handle_card_checking_contact():
    card_id = str(sys.argv[1]).split(";")[0]
    pin_id =str(sys.argv[1]).split(";")[1]

    if has_numbers_only(card_id,CARD_NUMBER_SIZE) and has_numbers_only(pin_id, CARD_PIN_SIZE) and handle_arg_error():
        nfc_rest.send_contact_info(card_id, pin_id)
        print("Tests are ok")
    else:
        logging.error("############################################")
        logging.error("<---> /!\ BAD Information from device /!\ <--->")
        logging.error("############################################")


def has_numbers_only(inputCardNumber, length):
    correct = True
    for char in inputCardNumber:
        if char.isdigit() and len(inputCardNumber) == length:
            print("is ok")
        else:
            logging.error("is ko")
            correct = False
    return correct


if __name__ == '__main__':
    logging.info("############################################")
    logging.info("<---> !!! Contact from client device!!! <--->")
    logging.info("############################################")

    handle_card_checking_contact()
