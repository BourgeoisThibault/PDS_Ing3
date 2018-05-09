#
# @author ABID BUTT Usman
#
from flask import session
import unittest
import os
import tempfile
# from unittest.mock import MagicMock

from app import rest_utils
from app.controller import app
from flask.ext.session import Session


class DabAppTestCase(unittest.TestCase):

    # initialization logic for the test suite declared in the test module
    # code that is executed before all tests in one test run
    @classmethod
    def setUpClass(cls):
        pass
        # clean up logic for the test suite declared in the test module

    # code that is executed after all tests in one test run
    @classmethod
    def tearDownClass(cls):
        pass

    def setUp(self):
        self.app = app.test_client()
        db = {"step":"FIRST"}

        # db["step"] = "toto"
        print(db["step"])

    def test_home(self):
        response = self.app.get('/')
        assert "ok"



    # initialization logic
    # code that is executed before each test
    #     app.secret_key = os.urandom(24)
    #
    #     cls.app = app.test_client()

    # clean up logic
    # code that is executed after each test
    def tearDown(self):
        pass

    # def test_home_status_code(self):
    #     # sends HTTP GET request to the application
    #     # on the specified path
    #
    #     result = self.app.get('/')
    #
    #     # assert the status code of the response
    #     self.assertEqual(result.status_code, 200)

    # def test_home_status_code(self):
    #     # sends HTTP GET request to the application
    #     # on the specified path
    #
    #     result = self.app.get('/')
    #
    #     # assert the status code of the response
    #     self.assertEqual(result.status_code, 200)
    #
    # def test_home_status_code(self):
    #     # sends HTTP GET request to the application
    #     # on the specified path
    #
    #     result = self.app.get('/')
    #
    #     # assert the status code of the response
    #     self.assertEqual(result.status_code, 200)

    # runs the unit tests in the module


if __name__ == '__main__':
    unittest.main()
