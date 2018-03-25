
import os,json
import logging
import rest_utils

from flask import Flask, request, render_template, jsonify
from flask_bootstrap import Bootstrap
from flask.ext.cors import CORS, cross_origin
from flask.ext.session import Session

from datetime import date
from flask_socketio import SocketIO,send,emit

from random import random
from time import sleep
from threading import Thread, Event

from app.file_checking_thread import CheckThread

from app import socketio, app

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
def card_checking():
    if rest_utils.check_valid_card("214", "215"):
        socketio.emit('newnumber', {'number': 1}, namespace='/test')
    else:
        print("Envoi de ko ")
        socketio.emit('newnumber', {'number': 2}, namespace='/test')

    return "ok"



def valid_transac():
    if os.path.isfile(PATH):
        with open(PATH) as location_data:
            FILE = json.load(location_data)
            for value in FILE:
                if value['nfc2'] == 1:
                    return True
                else:
                    return False
                print "Contenu : "+str(value['server2'])
    else:
        logging.warning('Fichier introuvable !')
    return False


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


@app.route('/la')
def ici():
    return "Le chemin de 'ici' est : " + request.path


@app.route('/pinui/_check_data')
@cross_origin(origin='*',headers=['Content-Type','Authorization'])
def check():
    data = True
    print("en attente ....")
    sleep(5)
    if data:
        return jsonify(result="ok")
    else:
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


@app.route('/_add_numbers')
@cross_origin(origin='*',headers=['Content-Type','Authorization'])
def add_numbers():
    a = request.args.get('a', 0, type=int)
    b = request.args.get('b', 0, type=int)
    return jsonify(result=a + b)


@app.route('/pinui')
def punui():
    mots = "test"
    return render_template('pinui.html')



@app.route('/')
def accueil():
    d = date.today().isoformat()
    return render_template('accueil.html')


@app.route('/first_loading')
def first_contact():
    socketio.emit('newnumber', {'number': 1}, namespace='/test')
    return "ok"


@app.route('/pin')
def pinui():
    d = "Entrez code pin"
    emit('newnumber', {'number': 123456}, namespace='/test')
    return "ok"
    # return render_template('accueil.html', la_date=d)


@socketio.on('my event')
def test_message(message):  # test_message() is the event callback function.
    emit('my response', {'data': 'got it!'})  # Trigger a new event called "my response"


@socketio.on('connect', namespace='/valid_transac')
def test_connect():
    logging.info('Client 2nd contact !')


@socketio.on('connect', namespace='/test')
def test_connect():
    # need visibility of the global thread object
    global thread
    print('Client connected')

    #  Start the thread which checks json file.
    if not thread.isAlive():
        print "Starting Thread"
        thread = CheckThread()
        thread.start()

