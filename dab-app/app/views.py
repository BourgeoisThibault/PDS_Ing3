#
#@author ABID BUTT Usman
#


import logging
import rest_utils

from flask import Flask, request, render_template, jsonify

from datetime import date
from flask_socketio import SocketIO,send,emit

from time import sleep
from threading import Thread, Event

from app.pooling_socket_thread import CheckThread

from app import socketio, app
from app import logging


thread = Thread()
thread_stop_event = Event()

AMOUNT_STATEFULL = 0

@app.route('/card_checking')
def card_checking():
    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    logging.info(card_id + " - " + pin)
    if rest_utils.check_valid_card(card_id, pin):
        socketio.emit('response_card_checking', {'code': 200,'card_id': card_id, 'pin': pin}, namespace='/home_pool')
        return "ok", 200
    else:
        print("Envoi de ko ")
        socketio.emit('response_card_checking', {'code': 401}, namespace='/home_pool')
        return "ko", 401


@app.route('/confirm_transac')
def confirm_transac():

    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    amount = request.args.get('amount')
    amount = AMOUNT_STATEFULL

    print(AMOUNT_STATEFULL)
    logging.info("Card id + " + str(card_id) + " - PIN : " + str(pin) + " - Amount2 : " + str(amount))

    if rest_utils.check_confirm_transac(card_id, pin, amount):
        socketio.emit('confirm_transac', {'code': 200}, namespace='/confirm_transac')
        return "ok",200

    else:
        print("Envoi de ko ")
        socketio.emit('confirm_transac', {'code': 401}, namespace='/confirm_transac')
        return "ko", 401


@socketio.on_error()
def error_handler(e):
    pass


@socketio.on_error_default
def default_error_handler(e):
    logging.warning(request.event["args"])
    logging.warning(request.event["message"])


@app.route('/pinui/_check_data')
def check_valid_transac():
    print("en attente ....")
    sleep(2)

    card_id = request.args.get('card_id')
    pin = request.args.get('pin_id')
    amount = request.args.get('amount')
    AMOUNT_STATEFULL = 50
    print("Card id + " + card_id + " - PIN : " + pin + " - Amount2 : " + amount)

    if rest_utils.check_valid_transac(card_id, pin, 123):
        return jsonify(response=200)
    else:
        print("Envoi de ko ")
        return jsonify(response=401)


@app.route('/pinui/_last_checking')
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

@app.route('/error')
def errorPage():
    d = date.today().isoformat()
    return render_template('error_page.html', messageError="La carte n'est pas reconnu")

@app.route('/help')
def helpPage():
    d = date.today().isoformat()
    return render_template('help_page.html')

@app.route('/success')
def successPage():
    return render_template('success_page.html')

@app.route('/testing')
def tetPge():
    return render_template('pinui.html')

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
        logging.info("Starting Thread")
        thread = CheckThread()
        thread.start()

