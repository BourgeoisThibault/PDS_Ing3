#
#@author ABID BUTT Usman
#


import os,json
import logging
import rest_utils

from flask import Flask, request, render_template, jsonify
from flask_bootstrap import Bootstrap
from flask.ext.cors import CORS, cross_origin

from datetime import date
from flask_socketio import SocketIO,send,emit

from random import random
from time import sleep
from threading import Thread, Event

from app.file_checking_thread import CheckThread

from app import socketio, app

logging.basicConfig(filename='dap-app.log',level=logging.DEBUG)




PATH = "card_file.json"
FILE = {}


thread = Thread()
thread_stop_event = Event()



def card_exists():
    if os.path.isfile(PATH):
        with open(PATH) as location_data:
            FILE = json.load(location_data)
            for value in FILE:
                if value["nfc1"] != 0:
                    print "AVANT APPEL getAccountsList"
                    print "Reponse accountList : "+str(rest_utils.getAccountsList(value["nfc1"]))
                    return rest_utils.getAccountsList(value["nfc1"])
                else:
                    return "none"
                print "Contenu : "+str(value['nfc1'])

    else:
        logging.warning('Fichier introuvable !')
        return False


@app.route('/card_checking')
@cross_origin(origin='*',headers=['Content-Type','Authorization'])
def card_checking():
    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    logging.info(card_id + " - " + pin)
    if rest_utils.check_valid_card(card_id, pin):
        socketio.emit('newnumber', {'number': 1,'card_id': card_id, 'pin': pin}, namespace='/home_pool')
        return "ok", 200
    else:
        print("Envoi de ko ")
        socketio.emit('newnumber', {'number': 2}, namespace='/home_pool')
        return "ko", 401





# def valid_transac():
#     if os.path.isfile(PATH):
#         with open(PATH) as location_data:
#             FILE = json.load(location_data)
#             for value in FILE:
#                 if value['nfc2'] == 1:
#                     return True
#                 else:
#                     return False
#                 print "Contenu : "+str(value['server2'])
#     else:
#         logging.warning('Fichier introuvable !')
#     return False

@app.route('/confirm_transac')
def confirm_transac():

    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    amount = request.args.get('amount')

    logging.info("Card id + " + card_id + " - PIN : " + pin + " - Amount : " + amount)

    if rest_utils.check_confirm_transac(card_id, pin, amount):
        socketio.emit('confirm_transac', {'conf': 'VALID'}, namespace='/confirm_transac')
        return "ok",200

    else:
        print("Envoi de ko ")
        socketio.emit('confirm_transac', {'conf': 'NOT_VALID'}, namespace='/confirm_transac')
        return "ko", 401


def remove_card():
    #FILE["server1"] = 0
    #FILE["server2"] = 0
    print("Removing card...")
    FILE = [{"nfc1": 0, "nfc2": 0}]
    with open(PATH, "w") as jsonFile:
        json.dump(FILE, jsonFile)
    print("Remove card : OK")


@socketio.on_error()
def error_handler(e):
    pass


@socketio.on_error_default
def default_error_handler(e):
    logging.warning(request.event["args"])
    logging.warning(request.event["message"])


@app.route('/pinui/_check_data')
@cross_origin(origin='*',headers=['Content-Type','Authorization'])
def check_valid_transac():
    print("en attente ....")
    sleep(2)

    card_id = request.args.get('card_id')
    pin = request.args.get('pin_id')
    amount = request.args.get('amount')

    print("Card id + " + card_id + " - PIN : " + pin + " - Amount : " + amount)

    if rest_utils.check_valid_transac(card_id, pin, 123):
        return jsonify(result="ok")
    else:
        print("Envoi de ko ")
        return jsonify(result="ko")



@app.route('/pinui/_last_checking')
@cross_origin(origin='*', headers=['Content-Type', 'Authorization'])
def last_check():
    data = True
    logging.info('en attente de last_checking....')

    sleep(5)
    if data:
        return jsonify(result="ok")
    else:
        return jsonify(result="ko")


@app.errorhandler(404)
def page_not_found(error):
    return render_template('page_not_found.html'), 404


@app.route('/pinui', methods=['POST'])
def punui():
    mots = "test"
    # TODO save these values in SESSION...
    card = request.form['card_id']
    pin = request.form['pin']
    print("INFO : "+card+"  "+pin)
    return render_template('pinui.html',card_id=card, pin_id=pin)


@app.route('/')
def home():
    d = date.today().isoformat()
    return render_template('home.html')


@socketio.on('my event')
def test_message(message):  # test_message() is the event callback function.
    emit('my response', {'data': 'got it!'})  # Trigger a new event called "my response"


@socketio.on('connect', namespace='/confirm_transac')
def test_connect():
    logging.info('Client 2nd front page connected !')

@socketio.on('connect', namespace='/home_pool')
def test_connect2():
    # need visibility of the global thread object
    global thread
    print('Client connected')

    #  Start the thread which loop
    if not thread.isAlive():
        print "Starting Thread"
        thread = CheckThread()
        thread.start()

