import pip

def install(package):
    pip.main(['install', package])

if __name__ == '__main__':
    install('Flask')
    install('Bootstrap')
    install('flask-cors')
    install('flask_bootstrap')
    install('flask-socketio')
    install('flask.ext.cors')
    install('Flask-Session')
    install('requests')


