import os
from app import app
from flask_session import Session
#sess = Session()

app.secret_key = os.urandom(24)
#app.config['SESSION_TYPE'] = 'filesystem'
#sess.permanent = True
#sess.init_app(app)

if __name__ == '__main__':
    app.run(host = '0.0.0.0',port=5000)
