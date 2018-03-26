#from views import remove_card, socketio, thread_stop_event, valid_transac
from threading import Thread, Event

import views
from time import sleep


class CheckThread(Thread):
    def __init__(self):
        self.delay = 1
        super(CheckThread, self).__init__()

    def file_checking_thread(self):
        #logging.info('Dans le Thread....')

        while not views.thread_stop_event.isSet():
            value = views.card_exists()
            if value == "ok":
                views.socketio.emit('newnumber', {'number': 1}, namespace='/test')

            else:
                if value == "none":
                    views.socketio.emit('newnumber', {'number': 0}, namespace='/test')
                    print "none"
                else:
                    print("Envoi de ko ")
                    views.socketio.emit('newnumber', {'number': 2}, namespace='/test')
            
            # valid_transac = views.valid_transac()
            # if valid_transac:
            #     views.socketio.emit('valid_transac', {'conf': 'VALID'}, namespace='/valid_transac')
            #     views.remove_card()
            # else:
            #     views.socketio.emit('valid_transac', {'conf': 'NOT_VALID'}, namespace='/valid_transac')
            sleep(5)

    def run(self):
        self.file_checking_thread()
