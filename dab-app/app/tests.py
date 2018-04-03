#
#@author ABID BUTT Usman
#

import unittest

from app.views import app


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

        # initialization logic
    # code that is executed before each test
    def setUp(self):
        self.app = app.test_client()



# clean up logic
    # code that is executed after each test
    def tearDown(self):
        pass

    def test_home_status_code(self):
        # sends HTTP GET request to the application
        # on the specified path

        result = self.app.get('/')

        # assert the status code of the response
        self.assertEqual(result.status_code, 200)


    # runs the unit tests in the module
if __name__ == '__main__':
    tests.main()