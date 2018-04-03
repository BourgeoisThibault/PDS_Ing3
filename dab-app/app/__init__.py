from flask import Flask, request, render_template, jsonify
from flask_bootstrap import Bootstrap
from flask_socketio import SocketIO,send,emit
import logging

logging.basicConfig(filename='dap-app.log', level=logging.DEBUG)




async_mode = None

app = Flask(__name__)
Bootstrap(app)

#app.config['SECRET_KEY'] = 'secret!'
#socketio = SocketIO(app)
socketio = SocketIO(app)


from app import views
