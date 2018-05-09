#
#@author ABID BUTT Usman
#

#from views import remove_card, socketio, thread_stop_event, valid_transac
from threading import Thread, Event

import controller
from time import sleep


class CheckThread(Thread):
    def __init__(self):
        self.delay = 1
        super(CheckThread, self).__init__()

    def pooling_Thread(self):
        #logging.info('Dans le Thread....')

        while not controller.thread_stop_event.isSet():
            controller.socketio.emit('response_card_checking', {'code': 0}, namespace='/home_pool')
            sleep(1)
            controller.socketio.emit('confirm_transac', {'conf': 'NOT_VALID'}, namespace='/confirm_transac')

    def run(self):
        self.pooling_Thread()
