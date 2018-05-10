from flask import Flask, request, render_template, jsonify
from flask_bootstrap import Bootstrap
from flask_socketio import SocketIO,send,emit
import logging
import os




async_mode = None

app = Flask(__name__)
Bootstrap(app)
socketio = SocketIO(app)


from app import controller
