package com.gate.rest;

import java.io.IOException;

import org.apache.http.HttpException;

import com.alibaba.fastjson.JSONObject;
import com.gate.rest.stock.IStockRestApi;
import com.gate.rest.stock.impl.StockRestApi;


public class StockClient {

	public static void main(String[] args) throws HttpException, IOException {

		String api_key = "";
		String secret_key = "";
		String url_prex = "https://data.gate.io";

		IStockRestApi stockGet = new StockRestApi(url_prex);

		IStockRestApi stockPost = new StockRestApi(url_prex, api_key, secret_key);

		// 所有交易对
		// String pairs = stockGet.pairs();
		// System.out.println(pairs);

		// 交易市场订单参数
		// String marketinfo = stockGet.marketinfo();
		// System.out.println(marketinfo);

		// 交易市场详细行情
		// String marketlist = stockGet.marketlist();
		// System.out.println(marketlist);

		// 所有交易行情
		// String tickers = stockGet.tickers();
		// System.out.println(tickers);

		// 单项交易行情
		// String ticker = stockGet.ticker("eth_btc");
		// System.out.println(ticker);

		// 市场深度
		// String orderBook = stockGet.orderBook("eth_btc");
		// System.out.println(orderBook);

		// 历史成交记录
		// String tradeHistory = stockGet.tradeHistory("eth_btc");
		// System.out.println(tradeHistory);

		// 获取帐号资金余额
		// String balance = stockPost.balance();
		// System.out.println(balance);

		// 获取充值地址
		// String depositAddress = stockPost.depositAddress("btc");
		// System.out.println(depositAddress);

		// 获取充值提现历史
		// String depositsWithdrawals = stockPost.depositsWithdrawals("1469092370",
		// "1669092370");
		// System.out.println(depositsWithdrawals);

		// 下单买入
		// String buy = stockPost.buy("ltc_btc", "999","123");
		// System.out.println(buy);

		// 下单卖出
		// String sell = stockPost.sell("ltc_btc", "999","123");
		// System.out.println(sell);

		// 取消下单
		// String cancelOrder = stockPost.cancelOrder("123456789", "ltc_btc");
		// System.out.println(cancelOrder);

		// 取消所有下单
		// String cancelAllOrders = stockPost.cancelAllOrders("1", "ltc_btc");
		// System.out.println(cancelAllOrders);

		// 获取下单状态
		// String getOrder = stockPost.getOrder("123456789", "ltc_btc");
		// System.out.println(getOrder);

		// 获取当前挂单列表
		// String openOrders = stockPost.openOrders();
		// System.out.println(openOrders);

		// 获取我的24小时内成交记录
		// String myTradeHistory = stockPost.myTradeHistory("eth_btc","123456789");
		// System.out.println(myTradeHistory);

		// 提现
		// String withdraw = stockPost.withdraw("btc","99","your addr");
		// System.out.println(withdraw);

	}
}
