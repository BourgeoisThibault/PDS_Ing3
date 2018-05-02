from flask import Flask, request, render_template, jsonify
from flask_bootstrap import Bootstrap
from flask_socketio import SocketIO,send,emit
import logging
import os
# logging.basicConfig(filename='dab-app.log', filemode='w', level=logging.INFO)
# logging.getLogger('socket.io').setLevel(logging.ERROR)
# logging.getLogger('socketio').setLevel(logging.ERROR)
# logging.getLogger('werkzeug').setLevel(logging.ERROR)
# logging.getLogger('engineio').setLevel(logging.ERROR)




async_mode = None

app = Flask(__name__)
Bootstrap(app)

#app.config['SECRET_KEY'] = 'secret!'
#socketio = SocketIO(app)
socketio = SocketIO(app)


from app import views
