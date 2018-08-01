using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Web;
using System.Net;
using Com.Gate.Rest;

namespace Com.Gate.Rest.Stock
{
    public interface IStockRestApi
    {
        String pairs();
        String marketinfo();
        String marketlist();
        String tickers();
        String ticker(String symbol);
        String orderBook(String symbol);
        String tradeHistory(String symbol);
        String balance();
        String depositAddress(String symbol);
        String depositsWithdrawals(String startTime, String endTime);
        String buy(String currencyPair, String rate, String amount);
        String sell(String currencyPair, String rate, String amount);
        String cancelOrder(String orderNumber, String currencyPair);
        String cancelOrders(String ordersJson) ;
        String cancelAllOrders(String type, String currencyPair);
        String getOrder(String orderNumber, String currencyPair);
        String openOrders();
        String openOrders(String currencyPair);
        String myTradeHistory(String currencyPair);
        String myTradeHistory(String currencyPair, String orderNumber);
        String withdraw(String currency, String amount, String address);
        String candlestick2(String symbol, String groupSec, String rangeHour);
    }

    class StockRestApi : IStockRestApi
    {
        private String url_prex;

        public StockRestApi(String url_prex)
        {
            this.url_prex = url_prex;
        }

    public const String PAIRS_URL = "/api2/1/pairs";


	public const String MARKETINFO_URL = "/api2/1/marketinfo";


	public const String MARKETLIST_URL = "/api2/1/marketlist";


	public const String TICKERS_URL = "/api2/1/tickers";

	
	public const String TICKER_URL = "/api2/1/ticker";

	
	public const String ORDERBOOK_URL = "/api2/1/orderBook";


	public const String BALANCE_URL = "/api2/1/private/balances";

	
	private const String DEPOSITADDRESS_URL = "/api2/1/private/depositAddress";

	
	private const String DEPOSITESWITHDRAWALS_URL = "/api2/1/private/depositsWithdrawals";

	
	private const String BUY_URL = "/api2/1/private/buy";

	
	private const String SELL_URL = "/api2/1/private/sell";

	
	private const String CANCELORDER_URL = "/api2/1/private/cancelOrder";
        
	private const String CANCELORDERS_URL = "/api2/1/private/cancelOrders";
	
	private const String CANDLESTICK2_URL = "/api2/1/candlestick2/";
	
	private const String CANCELALLORDERS_URL = "/api2/1/private/cancelAllOrders";

	
	private const String GETORDER_URL = "/api2/1/private/getOrder";


	private const String OPENORDERS_URL = "/api2/1/private/openOrders";

	
	private const String TRADEHISTORY_URL = "/api2/1/tradeHistory";


	private const String WITHDRAW_URL = "/api2/1/private/withdraw";
	

	private const String MYTRADEHISTORY_URL = "/api2/1/private/tradeHistory";


        public String pairs()
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            String result = httpUtil.requestHttpGet(url_prex, PAIRS_URL, param);
            return result;
        }
        
        public String marketinfo()
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            String result = httpUtil.requestHttpGet(url_prex, MARKETINFO_URL, param);
            return result;
        }

        public String marketlist()
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            String result = httpUtil.requestHttpGet(url_prex, MARKETLIST_URL, param);
            return result;
        }

        public String tickers()
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            String result = httpUtil.requestHttpGet(url_prex, TICKERS_URL, param);
            return result;
        }

        public String ticker(String symbol)
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";

            param += "/" + symbol;

            String result = httpUtil.requestHttpGet(url_prex, TICKER_URL + param, "");
            return result;
        }

        public String orderBook(String symbol)
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            if (!String.IsNullOrWhiteSpace(param))
            {
                if (String.Compare("", param) == 0)
                {
                    param += "/";
                }
                param += symbol;
            }
            String result = httpUtil.requestHttpGet(url_prex, ORDERBOOK_URL + param, param);
            return result;
        }

        public String tradeHistory(String symbol)
        {
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            if (String.IsNullOrWhiteSpace(param))
            {
                if (String.Compare("", param) == 0)
                {
                    param += "/";
                }
                param += symbol;
            }
            String result = httpUtil.requestHttpGet(url_prex, TRADEHISTORY_URL + param, "");
            return result;
        }

        public String balance()
        {
            Dictionary<String,String> Params = new Dictionary<String, String>();
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + BALANCE_URL, Params );
            return result;
        }

        public String depositAddress(String symbol)
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + DEPOSITADDRESS_URL, Params );
		    return result;
	    }

        public String depositsWithdrawals(String startTime, String endTime)
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("start", startTime);
		    Params.Add("end", endTime);
    
            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + DEPOSITESWITHDRAWALS_URL, Params);
		    return result;
	    }

        public String buy(String currencyPair, String rate, String amount)
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("currencyPair", currencyPair);
		    Params.Add("rate", rate);
		    Params.Add("amount", amount);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + BUY_URL, Params);
		    return result;
	    }

        public String sell(String currencyPair, String rate, String amount)
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("currencyPair", currencyPair);
            Params.Add("rate", rate);
            Params.Add("amount", amount);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + SELL_URL, Params);
		    return result;
	    }

        public String cancelOrder(String orderNumber, String currencyPair) 
       {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("orderNumber", orderNumber);
		    Params.Add("currencyPair", currencyPair);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + CANCELORDER_URL, Params);
		    return result;
	    }

        public String cancelOrders(String orderJson) 
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("orders_json", orderJson);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + CANCELORDERS_URL, Params);
		    return result;
	    }

        public String cancelAllOrders(String type, String currencyPair) 
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("type", type);
		    Params.Add("currencyPair", currencyPair);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + CANCELALLORDERS_URL, Params);
		    return result;
	    }

        public String getOrder(String orderNumber, String currencyPair)
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("orderNumber", orderNumber);
		    Params.Add("currencyPair", currencyPair);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + GETORDER_URL, Params);
		    return result;
	    }

        public String openOrders() 
       {

            Dictionary<String, String> Params = new Dictionary<String, String>();


            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + OPENORDERS_URL, Params);
		    return result;
	    }

        public String openOrders(String currencyPair) 
        {
		    if(currencyPair==null||"".Equals(currencyPair)) 
            {
			    return openOrders();
            }
            else 
            {
			    Dictionary<String, String> Params = new Dictionary<String, String>();
                Params.Add("currencyPair", currencyPair);
                HttpUtilManager httpUtil = HttpUtilManager.getInstance();
                String result = httpUtil.doRequest("data", "post", url_prex + OPENORDERS_URL, Params);
			    return result;
		    }
	    }

        public String myTradeHistory(String currencyPair) 
        {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("currencyPair", currencyPair);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + MYTRADEHISTORY_URL, Params);
		    return result;
	    }

        public String myTradeHistory(String currencyPair, String orderNumber)  
        {
		    if(orderNumber==null||"".Equals(orderNumber)) 
            {
			    return myTradeHistory(currencyPair);
            }
            else 
            {
                Dictionary<String, String> Params = new Dictionary<String, String>();
                Params.Add("currencyPair", currencyPair);
                Params.Add("orderNumber", orderNumber);

                HttpUtilManager httpUtil = HttpUtilManager.getInstance();
                String result = httpUtil.doRequest("data", "post", url_prex + MYTRADEHISTORY_URL, Params);
			    return result;
		    }
	    }

        public String withdraw(String currency, String amount, String address) 
       {

            Dictionary<String, String> Params = new Dictionary<String, String>();
            Params.Add("currency", currency);
		    Params.Add("amount", amount);
		    Params.Add("address", address);

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String result = httpUtil.doRequest("data", "post", url_prex + WITHDRAW_URL, Params);
		    return result;
	    }

        public String candlestick2(String symbol, String groupSec, String rangeHour)
        {

            HttpUtilManager httpUtil = HttpUtilManager.getInstance();
            String param = "";
            if ((!"".Equals(symbol))&&symbol != null) 
            {
                param += symbol;
			    if((!"".Equals(groupSec)) && groupSec != null) 
                {
				    param += "?group_sec=" + groupSec;
				    if((!"".Equals(rangeHour)) && rangeHour != null) 
                    {
					    param += "&range_hour=" + rangeHour;
				    }
			    }
                else {
				    if((!"".Equals(rangeHour)) && rangeHour != null) 
                    {
					    param += "?range_hour=" + rangeHour;
				    }
			    }
		    }
            String result = httpUtil.doRequest("data","POST", url_prex + CANDLESTICK2_URL + param, new Dictionary<String, String>());
		    return result;
        }

    }
}
