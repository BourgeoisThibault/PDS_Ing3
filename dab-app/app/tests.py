#
# @author ABID BUTT Usman
#
import unittest
# import controller
import fakeredis
from mock import mock, patch
# import rest_utils
from app.controller import app
import rest_utils


class DabAppTestCase(unittest.TestCase):

    # initialization logic for the test suite declared in the test module

    def setUp(self):
        # out = fakeredis.FakeStrictRedis()
        self.app = app.test_client()

    def test_redis(self):
        response = self.app.get('/')
        self.assertEqual(200, response.status_code)

    def test_error(self):
        response = self.app.get('/error')
        self.assertEqual(404, response.status_code)

    def test_help(self):
        response = self.app.get('/help')
        self.assertEqual(200, response.status_code)

    def test_sucess(self):
        response = self.app.get('/success')
        self.assertEqual(200, response.status_code)

    def test_withdrawal_ui(self):
        # response = self.app.post('/withdrawdal_ui?card_id=1111111111&&pin_id=mypass')
        response = self.app.post('/withdrawal_ui',data=dict(card_id='data1', pin='data2'))
        # assert "ok"
        self.assertEqual(200, response.status_code)

    def test_page_not_found(self):
        response = self.app.get('404')
        self.assertEqual(404, response.status_code)

    @patch('rest_utils.check_valid_transac')
    def test_check_valid_transac(self,out):
        out = True
        response = self.app.get('/')
        response = self.app.get('/withdrawal_ui/confirme', data=dict(amount='data1'))
        self.assertEqual(200, response.status_code)

    @patch('rest_utils.check_valid_transac')
    def test_check__valid_transac(self,out):
        out = False
        response = self.app.get('/')
        response = self.app.get('/withdrawal_ui/confirme', data=dict(amount='data1'))
        self.assertEqual(200, response.status_code)

    @patch('rest_utils.check_valid_transac')
    @patch('rest_utils.check_confirm_transac')
    def test_not_valid_card_checking(self, out,out2):
        out = True
        out2 = True
        result = self.app.get('/')
        response = self.app.get('/card_checking?card_id=data1&&pin=data2')
        response = self.app.get('/withdrawal_ui/confirme', data=dict(amount='data1'))
        response = self.app.post('/withdrawal_ui',data=dict(card_id='data1', pin='data2'))
        response = self.app.get('/card_checking?card_id=data1&&pin=data3')
        self.assertEqual(401, response.status_code)

    # @patch('rest_utils.check_valid_card')
    # def test_card_checking(self, out):
    #     out = True
    #     result = self.app.get('/')
    #     response = self.app.get('/card_checking?card_id=12&&pin=mypass')
    #     self.assertEqual(200, response.status_code)

    def test_home_status_code(self):
        # sends HTTP GET request to the application
        # on the specified path
        result = self.app.get('/')

        # assert the status code of the response
        self.assertEqual(result.status_code, 200)

    # runs the unit tests in the module


if __name__ == '__main__':
    unittest.main()
