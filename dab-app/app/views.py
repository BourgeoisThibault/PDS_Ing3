#
#@author ABID BUTT Usman
#

import os
import logging
import rest_utils

from flask import Flask, request, render_template, jsonify, session
from flask_session import Session

from datetime import date
from flask_socketio import SocketIO,send,emit

from time import sleep
from threading import Thread, Event

from app.pooling_socket_thread import CheckThread
import redis_management

from app import socketio, app
from app import logging

#Session(app)


REDIS_CONNECTION = redis_management.create_connection()
redis_management.initialize_keys(REDIS_CONNECTION)


thread = Thread()
thread_stop_event = Event()

@app.route('/card_checking')
def card_checking():
    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    step = redis_management.get_step_key('step')
#Add theses values in session
#    print("CARD CHECKING STEP : " + session.get('step'))
    print("CARD CHECKING STEP : " + step)
    logging.info(card_id + " - " + pin)
    if step == "FIRST":
        if rest_utils.check_valid_card(card_id, pin):
            socketio.emit('response_card_checking', {'code': 200,'card_id': card_id, 'pin': pin}, namespace='/home_pool')
            return "ok", 200
        else:
            print("Envoi de ko ")
            socketio.emit('response_card_checking', {'code': 401}, namespace='/home_pool')
            return "ko", 401
    else:
        return confirm_transac()


def confirm_transac():
    card_id = request.args.get('card_id')
    pin = request.args.get('pin')
    amount = session.get('amount')

    r_card_id = redis_management.get_card_id(REDIS_CONNECTION)
    r_pin = redis_management.get_pin(REDIS_CONNECTION)
    r_amount = redis_management.get_amount(REDIS_CONNECTION)

    logging.info("CONFIRM TRANSAC Card id + " + str(card_id) + " - PIN : " + str(pin) + " - Amount2 : " + str(amount) + "STEP " + session.get('step'))

    #if card_id stored in redis DB  does not match with request arg card_id, return forbidden code 403
    if card_id != r_card_id or pin != r_pin:
        socketio.emit('confirm_transac', {'code': 403}, namespace='/confirm_transac')
        return "ko", 403
    else:#double verification
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


@app.route('/withdrawal_ui/_check_data')
def check_valid_transac():
    print("en attente ....")
#    session['step'] = "SECOND"
    redis_management.set_step_key("SECOND", REDIS_CONNECTION)
    sleep(2)

    #card_id = session.get('card_id')
    #pin = session.get('pin')
    card_id = redis_management.get_card_id(REDIS_CONNECTION)
    pin = redis_management.get_pin(REDIS_CONNECTION)
    amount = request.args.get('amount')
    #Add amount in session for last checking operation
    #session['amount'] = amount
    redis_management.set_amount(amount, REDIS_CONNECTION)
#    print("SESSION VALUES : Card id + " + session.get('card_id') + " PIN  + " + session.get('pin') + "Amount : " + session.get('amount'))
    print("REDIS VALUES : Card id + " + card_id + " PIN  + " + pin + "Amount : " + amount)

    if rest_utils.check_valid_transac(card_id, pin, amount):
        return jsonify(response=200, amount=amount)
    else:
        print("Envoi de ko ")
        return jsonify(response=401)


@app.route('/withdrawal_ui/_last_checking')
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
    return render_template('withdrawal_ui.html')


@app.route('/withdrawal_ui', methods=['POST'])
def withdrawal_ui():
    mots = "test"
    # TODO save these values in REDIS DB...
    # session['card_id'] = request.form['card_id']
    # session['pin'] = request.form['pin']
    card = request.form['card_id']
    pin = request.form['pin']

    redis_management.set_card_id(card, REDIS_CONNECTION)
    redis_management.set_pin(pin, REDIS_CONNECTION)

    print("INFO : "+card+"  "+pin)
    return render_template('withdrawal_ui.html',card_id=card, pin_id=pin)


@app.route('/')
def home():
    d = date.today().isoformat()
#    session['step'] = "FIRST"
    redis_management.initialize_keys(REDIS_CONNECTION)
    redis_management.set_step_key("FIRST", REDIS_CONNECTION)
    print("STEP BY HOME FROM REDIS : " + redis_management.get_step_key())
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

