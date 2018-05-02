import os
from app import app
import logging
from flask_session import Session
#sess = Session()

app.secret_key = os.urandom(24)
#app.config['SESSION_TYPE'] = 'filesystem'
#sess.permanent = True
#sess.init_app(app)


def main_log():
    logging.basicConfig(filename='dab-app.log', filemode='w', level=logging.INFO)
    logging.getLogger('socket.io').setLevel(logging.ERROR)
    logging.getLogger('socketio').setLevel(logging.ERROR)
    logging.getLogger('werkzeug').setLevel(logging.ERROR)
    logging.getLogger('engineio').setLevel(logging.ERROR)
    logging.info('Started')
    logging.info('Finished')


if __name__ == '__main__':
    main_log()
    app.run(host = '0.0.0.0',port=5000)
