#!/usr/bin/python
# -*- coding: utf-8 -*-
# encoding: utf-8

'''
Provide user specific data and interact with gate.io
'''

from gateAPI import GateIO

## 填写 apiKey APISECRET
apiKey = 'your api key'
secretKey = 'your api secret'
## address
btcAddress = 'your btc address'


## Provide constants

API_URL = 'data.gate.io'

## Create a gate class instance

gate = GateIO(API_URL, apiKey, secretKey)


# 所有交易对
print(gate.pairs())


## Below, use general methods that query the exchange

# 市场订单参数
# print(gate.marketinfo())

# 交易市场详细行情
# print(gate.marketlist())

# 所有交易行情
# print(gate.tickers())
# 所有交易对的市场深度
# print(gate.orderBooks())

# 获取下单状态
# print(gate.openOrders())


## Below, use methods that make use of the users keys

# 单项交易行情
# print(gate.ticker('btc_usdt'))

# 单项交易对的市场深度
# print(gate.orderBook('btc_usdt'))

# 单项交易对的市场深度
# print(gate.tradeHistory('btc_usdt'))

# 获取账号资金
# print(gate.balances())

# 获取充值地址
# print(gate.depositAddres('btc'))

# 获取充值提现历史记录
# print(gate.depositsWithdrawals('1469092370', '1569092370'))

# 下单交易买入
# print(gate.buy('etc_btc', '0.001', '123'))

# 下单交易买入
# print(gate.sell('etc_btc', '0.001', '123'))

# 取消下单
# print(gate.cancelOrder('267040896', 'etc_btc'))

# 取消所有订单
# print(gate.cancelAllOrders('0', 'etc_btc'))

# 获取下单状态
# print(gate.getOrder('267040896', 'eth_btc'))

# 获取我的24小时内成交记录
# print(gate.mytradeHistory('etc_btc', '267040896'))

# 提现
# print(gate.withdraw('btc', '88', btcAddress))
