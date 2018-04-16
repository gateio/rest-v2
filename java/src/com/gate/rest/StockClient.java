package com.gate.rest;

import java.io.IOException;

import org.apache.http.HttpException;

import com.alibaba.fastjson.JSONObject;
import com.gate.rest.stock.IStockRestApi;
import com.gate.rest.stock.impl.StockRestApi;


public class StockClient {

	public static void main(String[] args) throws HttpException, IOException {

		
		String query_url = "https://data.gateio.io";
		String trade_url = "https://api.gateio.io";


		IStockRestApi stockGet = new StockRestApi(query_url);

		IStockRestApi stockPost = new StockRestApi(trade_url);

		// All trading Pairs
		 String pairs = stockGet.pairs();
		 System.out.println(pairs);

		// Market Info
		// String marketinfo = stockGet.marketinfo();
		// System.out.println(marketinfo);

		// Market Details
		// String marketlist = stockGet.marketlist();
		// System.out.println(marketlist);

		// Tickers 
//		 String tickers = stockGet.tickers();
//		 System.out.println(tickers);

		// Ticker 
		// String ticker = stockGet.ticker("eth_btc");
		// System.out.println(ticker);

		// Depth
		// String orderBook = stockGet.orderBook("eth_btc");
		// System.out.println(orderBook);

		// Trade History
		// String tradeHistory = stockGet.tradeHistory("eth_btc");
		// System.out.println(tradeHistory);

		// Get account fund balances
//		 String balance = stockPost.balance();
//		 System.out.println(balance);

		// get deposit address
		// String depositAddress = stockPost.depositAddress("btc");
		// System.out.println(depositAddress);

		// get deposit withdrawal history 
		// String depositsWithdrawals = stockPost.depositsWithdrawals("1469092370",
		// "1669092370");
		// System.out.println(depositsWithdrawals);

		// Place order buy
		// String buy = stockPost.buy("ltc_btc", "999","123");
		// System.out.println(buy);

		// Place order sell
		// String sell = stockPost.sell("ltc_btc", "999","123");
		// System.out.println(sell);

		// Cancel order
		// String cancelOrder = stockPost.cancelOrder("123456789", "ltc_btc");
		// System.out.println(cancelOrder);

		// Cancel all orders
		// String cancelAllOrders = stockPost.cancelAllOrders("1", "ltc_btc");
		// System.out.println(cancelAllOrders);

		// Get order status
		// String getOrder = stockPost.getOrder("123456789", "ltc_btc");
		// System.out.println(getOrder);

		// Get my open order list
		// String openOrders = stockPost.openOrders();
		// System.out.println(openOrders);

		// Get my last 24h trades
		// String myTradeHistory = stockPost.myTradeHistory("eth_btc","123456789");
		// System.out.println(myTradeHistory);

		// withdrawal 
		// String withdraw = stockPost.withdraw("btc","99","your addr");
		// System.out.println(withdraw);

	}
}
