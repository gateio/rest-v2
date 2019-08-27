using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Com.Gate.Rest.Stock;
using Com.Gate.Rest;

namespace Com.Gate.Rest.Test
{
    class StockRestApiTest
    {
        private static String QURL = "https://data.gateio.co";
	    private static String TURL = "https://api.gateio.co";
	    private static IStockRestApi stockGet = new StockRestApi(QURL);
        private static IStockRestApi stockPost = new StockRestApi(TURL);

        static void Main(string[] args)
        {
            Console.Write(testMyTradeHistory("eth_usdt", "12345"));
        }

        private String testPairs()
        {
            String pairs = stockGet.pairs();
            return pairs;
        }

        private String testMarketlist()
        {
            String marketlist = stockGet.marketlist();
            return marketlist;
        }

        private String testTickers()
        {
            String tickers = stockGet.tickers();
            return tickers;
        }

        private String testTicker(String symbol)
        {
            String ticker = stockGet.ticker(symbol);
            return ticker;
        }

        private String testOrderBooks(String symbol)
        {
            String orderBooks = stockGet.orderBook(symbol);
            return orderBooks;
        }

        private String testTradeHistory(String symbol)
        {
            String tradeHistory = stockGet.tradeHistory(symbol);
            return tradeHistory;
        }

        private String testBalances()
        {
            String balance = stockPost.balance();
            return balance;
        }

        private String testDepositAddress(String symbol)
        {
            String depositAddress = stockPost.depositAddress(symbol);
            return depositAddress;
        }

        private String testDepositsWithdrawals(String startTime, String endTime)
        {
            String depositsWithdrawals = depositsWithdrawals = stockPost.depositsWithdrawals(startTime, endTime);
            return depositsWithdrawals;
        }

        private String testBuy(String currencyPair, String rate, String amount)
        {
            String buy = stockPost.buy(currencyPair, rate, amount);
            return buy;
        }

        private String testSell(String currencyPair, String rate, String amount)
        {
            String sell = stockPost.sell(currencyPair, rate, amount);
            return sell;
        }

        private String testCancelOrder(String orderNumber, String currencyPair)
        {
            String cancelOrder = stockPost.cancelOrder(orderNumber, currencyPair);
            return cancelOrder;
        }

        private String testCancelOrders(String ordersJson)
        {
            String cancelOrder = stockPost.cancelOrders(ordersJson); ;
            return cancelOrder;
        }

        private String testCancelAllOrders(String type, String currencyPair)
        {
            String cancelAllOrders = stockPost.cancelAllOrders(type, currencyPair);
            return cancelAllOrders;
        }

        private String testGetOrder(String orderNumber, String currencyPair)
        {
            String getOrder = stockPost.getOrder(orderNumber, currencyPair);
            return getOrder;
        }

        private String testOpenOrders(String currencyPair)
        {
            String openOrders = stockPost.openOrders(currencyPair);
            return openOrders;
        }

        private static String testMyTradeHistory(String currencyPair, String orderNumber)
        {
            String myTradeHistory = stockPost.myTradeHistory(currencyPair, orderNumber);
            return myTradeHistory;
        }

        private static String testWithdraw(String currency, String amount, String address)
        {
            String withdraw = stockPost.withdraw(currency, amount, address);
            return withdraw;
        }

        private String testCandlestick2(String symbol, String groupSec, String rangeHour)
        {
            String candlestick2 = stockGet.candlestick2(symbol, groupSec, rangeHour);
            return candlestick2;
        }
    }
}
