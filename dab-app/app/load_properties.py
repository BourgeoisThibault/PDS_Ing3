#
#@author ABID BUTT Usman
#
import json

import os

base_dir = os.path.dirname(__file__)
FILENAME=base_dir+'/properties.json'


def get_properties(key):
    with open(FILENAME) as json_data:
        d = json.load(json_data)
        value = d[key]
    return value
