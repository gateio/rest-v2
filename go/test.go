package main

import (
	"crypto/hmac"
	"crypto/sha512"
	// "encoding/hex"
	// "encoding/json"
	"net/http"
	// "net/url"
	// "sort"
	"io/ioutil"
	"strings"
	"fmt"
)

const KEY  = "your api key"; // gate.io 申请
const SECRET = "your api secret";  // gate.io 申请

func main() {

	// 方法调用

	// 获取系统支持所有交易对
	var ret string = getPairs()
	fmt.Println(ret)



}

// 返回所有系统支持的交易对
func getPairs() string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/pairs"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}

// 所有系统支持的交易市场的参数信息
func marketinfo() string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/marketinfo"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 返回所有系统支持的交易市场的详细行情和币种信息
func marketlist() string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/marketlist"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 所有交易行情
func tickers() string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/tickers"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 单项交易行情
func ticker(ticker string) string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/ticker" + "/" + ticker
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 市场深度
func orderBooks() string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/orderBooks"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 单项市场深度
func orderBook(params string) string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/orderBook/" + params
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 历史成交记录
func tradeHistory(params string) string {
	var method string = "GET"
	var url string = "http://data.gate.io/api2/1/tradeHistory/" + params
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取账号资金余额
func balances() string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/balances"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}



// 获取充值地址
func depositAddress(currency string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/depositAddress"
	var param string = "currency=" + currency
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取充值提现历史
func depositsWithdrawals(start string, end string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/depositsWithdrawals"
	var param string = "start=" + start + "&end=" + end
	var ret string = httpDo(method,url,param)
	return ret
}


// 下单交易买入
func buy(currencyPair string, rate string, amount string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/buy"
	var param string = "currencyPair=" + currencyPair + "&rate=" + rate + "&amount=" + amount
	var ret string = httpDo(method,url,param)
	return ret
}

// 下单交易卖出
func sell(currencyPair string, rate string, amount string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/sell"
	var param string = "currencyPair=" + currencyPair + "&rate=" + rate + "&amount=" + amount
	var ret string = httpDo(method,url,param)
	return ret
}


// 取消下单
func cancelOrder(orderNumber string, currencyPair string ) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/cancelOrder"
	var param string = "orderNumber=" + orderNumber + "&currencyPair=" + currencyPair
	var ret string = httpDo(method,url,param)
	return ret
}

// 取消全部下单
func cancelAllOrders( types string, currencyPair string ) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/cancelAllOrders"
	var param string = "type=" + types + "&currencyPair=" + currencyPair
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取订单状态
func getOrder( orderNumber string, currencyPair string ) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/getOrder"
	var param string = "orderNumber=" + orderNumber + "&currencyPair=" + currencyPair
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取我的当前挂单列表
func openOrders() string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/openOrders"
	var param string = ""
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取我的24小时内成交记录
func myTradeHistory( currencyPair string, orderNumber string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/tradeHistory"
	var param string = "orderNumber=" + orderNumber + "&currencyPair=" + currencyPair
	var ret string = httpDo(method,url,param)
	return ret
}


// 获取我的24小时内成交记录
func withdraw( currency string, amount string, address string) string {
	var method string = "POST"
	var url string = "https://api.gate.io/api2/1/private/withdraw"
	var param string = "currency=" + currency + "&amount=" + amount + "address=" + address
	var ret string = httpDo(method,url,param)
	return ret
}


func getSign( params string) string {
    key := []byte(SECRET)
    mac := hmac.New(sha512.New, key)
    mac.Write([]byte(params))
    return fmt.Sprintf("%x", mac.Sum(nil))
}
	
/**
*  http请求封装
*/	
func httpDo(method string,url string, param string) string {
    client := &http.Client{}
 
    req, err := http.NewRequest(method, url, strings.NewReader(param))
    if err != nil {
        // handle error
    }
    var sign string = getSign(param)
 
    req.Header.Set("Content-Type", "application/x-www-form-urlencoded")
    req.Header.Set("key", KEY)
    req.Header.Set("sign", sign)
 
    resp, err := client.Do(req)
 
    defer resp.Body.Close()
 
    body, err := ioutil.ReadAll(resp.Body)
    if err != nil {
        // handle error
    }
 	
 	return string(body);
}