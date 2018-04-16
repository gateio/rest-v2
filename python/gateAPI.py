#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Provide the GateIO class to abstract web interaction
'''

from HttpUtil import getSign, httpGet, httpPost

class GateIO:
    def __init__(self, url, apiKey, secretKey):
        self.__url = url
        self.__apiKey = apiKey
        self.__secretKey = secretKey

    ## General methods that query the exchange

    #所有交易对
    def pairs(self):
        URL = "/api2/1/pairs"
        params=''
        return httpGet(self.__url, URL, params)

    #市场订单参数
    def marketinfo(self):
        URL = "/api2/1/marketinfo"
        params=''
        return httpGet(self.__url, URL, params)

    #交易市场详细行情
    def marketlist(self):
        URL = "/api2/1/marketlist"
        params=''
        return httpGet(self.__url, URL, params)

    #所有交易行情
    def tickers(self):
        URL = "/api2/1/tickers"
        params=''
        return httpGet(self.__url, URL, params)

    # 所有交易对市场深度
    def orderBooks(self):
        URL = "/api2/1/orderBooks"
        param=''
        return httpGet(self.__url, URL, param)

    #单项交易行情
    def ticker(self, param):
        URL = "/api2/1/ticker"
        return httpGet(self.__url, URL, param)

    # 单项交易对市场深度
    def orderBook(self, param):
        URL = "/api2/1/orderBook"
        return httpGet(self.__url, URL, param)

    # 历史成交记录
    def tradeHistory(self, param):
        URL = "/api2/1/tradeHistory"
        return httpGet(self.__url, URL, param)

    ## Methods that make use of the users keys

    #获取帐号资金余额
    def balances(self):
        URL = "/api2/1/private/balances"
        param = {}
        return httpPost(self.__url, URL, param, self.__apiKey, self.__secretKey)

    # 获取充值地址
    def depositAddres(self,param):
        URL = "/api2/1/private/depositAddress"
        params = {'currency':param}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 获取充值提现历史
    def depositsWithdrawals(self, start,end):
        URL = "/api2/1/private/depositsWithdrawals"
        params = {'start': start,'end':end}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 买入
    def buy(self, currencyPair,rate, amount):
        URL = "/api2/1/private/buy"
        params = {'currencyPair': currencyPair,'rate':rate, 'amount':amount}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 卖出
    def sell(self, currencyPair, rate, amount):
        URL = "/api2/1/private/sell"
        params = {'currencyPair': currencyPair, 'rate': rate, 'amount': amount}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 取消订单
    def cancelOrder(self, orderNumber, currencyPair):
        URL = "/api2/1/private/cancelOrder"
        params = {'orderNumber': orderNumber, 'currencyPair': currencyPair}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 取消所有订单
    def cancelAllOrders(self, type, currencyPair):
        URL = "/api2/1/private/cancelAllOrders"
        params = {'type': type, 'currencyPair': currencyPair}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 获取下单状态
    def getOrder(self, orderNumber, currencyPair):
        URL = "/api2/1/private/getOrder"
        params = {'orderNumber': orderNumber, 'currencyPair': currencyPair}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 获取我的当前挂单列表
    def openOrders(self):
        URL = "/api2/1/private/openOrders"
        params = {}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 获取我的24小时内成交记录
    def mytradeHistory(self, currencyPair, orderNumber):
        URL = "/api2/1/private/tradeHistory"
        params = {'currencyPair': currencyPair, 'orderNumber': orderNumber}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)

    # 提现
    def withdraw(self, currency, amount, address):
        URL = "/api2/1/private/withdraw"
        params = {'currency': currency, 'amount': amount,'address':address}
        return httpPost(self.__url, URL, params, self.__apiKey, self.__secretKey)
