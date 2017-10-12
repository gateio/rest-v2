package com.gate.rest.stock;

import java.io.IOException;

import org.apache.http.HttpException;


public interface IStockRestApi {
	
		public String pairs() throws HttpException, IOException;
		public String marketinfo() throws HttpException, IOException;
		public String marketlist() throws HttpException, IOException;
		
		public String tickers() throws HttpException, IOException;
		public String ticker(String symbol) throws HttpException, IOException;
		public String orderBook(String symbol) throws HttpException, IOException;
		public String tradeHistory(String symbol) throws HttpException, IOException;
		public String balance() throws HttpException, IOException;
		public String depositAddress(String symbol) throws HttpException, IOException;
		public String depositsWithdrawals(String startTime,String endTime) throws HttpException, IOException;
		public String buy(String currencyPair,String rate, String amount) throws HttpException, IOException;
		public String sell(String currencyPair,String rate, String amount) throws HttpException, IOException;
		public String cancelOrder(String orderNumber, String currencyPair) throws HttpException, IOException;
		public String cancelAllOrders(String type, String currencyPair) throws HttpException, IOException;
		public String getOrder(String orderNumber, String currencyPair) throws HttpException, IOException;
		public String openOrders() throws HttpException, IOException;
		public String myTradeHistory(String currencyPair,String orderNumber) throws HttpException, IOException;
		public String withdraw(String currency,String amount, String address) throws HttpException, IOException;

}
