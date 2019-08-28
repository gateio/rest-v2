<?php
	
	function gate_query($path, array $req = array()) {
		
		// API settings, add your Key and Secret at here

        $key = "your api key";
		$secret = "your api secret";

		// generate a nonce as microtime, with as-string handling to avoid problems with 32bits systems
		$mt = explode(' ', microtime());
		$req['nonce'] = $mt[1].substr($mt[0], 2, 6);

		// generate the POST data string
		$post_data = http_build_query($req, '', '&');
		$sign = hash_hmac('sha512', urldecode($post_data), $secret);

		// generate the extra headers
		$headers = array(
			'KEY: '.$key,
			'SIGN: '.$sign,
            'Content-Type: application/x-www-form-urlencoded; charset=utf-8'
		);

		static $ch = null;
		if (is_null($ch)) {
			$ch = curl_init();
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_USERAGENT, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36');
		}

		curl_setopt($ch, CURLOPT_URL, 'http://api.gateio.co/api2/'.$path);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $post_data);
		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_HEADER, false);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, FALSE);


		// run the query
		$res = curl_exec($ch);
        $getinfo = curl_getinfo($ch);
//		echo $res;die;
		if ($res === false) throw new Exception('Could not get reply: '.curl_error($ch));
		//print_r($res);
		$dec = json_decode($res, true);
		if (!$dec) throw new Exception('Invalid data received, please make sure connection is working and requested API exists: '.$res);

		return $dec;
	}
 
	
	function curl_file_get_contents($url) {
		
		// our curl handle (initialize if required)
		static $ch = null;
		if (is_null($ch)) {
			$ch = curl_init();
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($ch, CURLOPT_USERAGENT, 
				'Mozilla/4.0 (compatible; gate PHP bot; '.php_uname('a').'; PHP/'.phpversion().')'
				);
		}
		curl_setopt($ch, CURLOPT_URL, 'https://data.gateio.io/api2/'.$url);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);

		// run the query
		$res = curl_exec($ch);
		if ($res === false) throw new Exception('Could not get reply: '.curl_error($ch));
		$dec = json_decode($res, true);
		if (!$dec) throw new Exception('Invalid data: '.$res);
		
		return $dec;
	}

	function get_top_rate($currency_pair, $type='BUY') {
		
		$url = '1/orderBook/'.strtoupper($currency_pair);
		$json = curl_file_get_contents($url);
		
		$rate = 0;

		if (strtoupper($type) == 'BUY') {
			$r =  $json['bids'][0];
			$rate = $r[0];
		} else  {
			$r = end($json['asks']);
			$rate = $r[0];
		}

		return $rate;
	}	
	
	function get_pairs() {
		
		$url = '1/pairs';
		$json = curl_file_get_contents($url);
		
		return $json;		
	}

	function get_marketinfo(){
		
		$url = '1/marketinfo';
		$json = curl_file_get_contents($url);
		
		return $json;		
	}


    function get_marketlist(){

        $url = '1/marketlist';
        $json = curl_file_get_contents($url);

        return $json;
    }

	function get_tickers(){
		
		$url = '1/tickers';
		$json = curl_file_get_contents($url);
		
		return $json;		
	}
	 
	function get_ticker($current_pairs){
		
		$url = '1/ticker/'.strtoupper($current_pairs);
		$json = curl_file_get_contents($url);
		
		return $json;		
	}
	 
	function get_orderbooks(){
		
		$url = '1/orderBooks';
		$json = curl_file_get_contents($url);
		
		return $json;
	}
	 
	function get_orderbook($current_pairs){
		
		$url = '1/orderBook/'.strtoupper($current_pairs);
		$json = curl_file_get_contents($url);
		
		return $json;
	}
	 
	function get_trade_history($current_pairs, $tid){
		
		$url = '1/tradeHistory/'.strtoupper($current_pairs).'/'.$tid;
		$json = curl_file_get_contents($url);
		
		return $json;
	}	
	
	function get_balances() {
		
		return gate_query('1/private/balances');
	}
	
	function get_order_trades($order_number) {
		
		return gate_query('1/private/orderTrades',
			array(
				'orderNumber' => $order_number
			)
		);
	}
	
	function withdraw($currency, $amount, $address) {
		
		return gate_query('1/private/withdraw',
			array(
				'currency' => strtoupper($currency),
				'amount' => $amount,
				'address' => $address
			)
		);
	}
	
	function get_order($order_number, $currency_pair) {
		
		return gate_query('1/private/getOrder',
			array(
				'orderNumber' => $order_number,
				'currencyPair' => strtoupper($currency_pair)
			)
		);
	}
	
	function cancel_order($order_number, $currency_pair) {
		
		return gate_query('1/private/cancelOrder',
			array(
				'orderNumber' => $order_number,
				'currencyPair' => strtoupper($currency_pair)
			)
		);
	}

    function cancel_orders($orders) {
        return gate_query('1/private/cancelOrders',
//           ['orders_json'=> $orders]
           ['orders_json'=>json_encode( $orders)]
        );
    }
	
	function cancel_all_orders($type, $currency_pair) {
		
		return gate_query('1/private/cancelAllOrders',
			array(
				'type' => $type,
				'currencyPair' => strtoupper($currency_pair)
			)
		);
	}
	
	function sell($currency_pair, $rate, $amount) {
		
		return gate_query('1/private/sell',
			array(
				'currencyPair' => strtoupper($currency_pair),
				'rate' => $rate,
				'amount' => $amount,
			)
		);
	}
	
	function buy($currency_pair, $rate, $amount) {
		
		return gate_query('1/private/buy',
			array(
				'currencyPair' => strtoupper($currency_pair),
				'rate' => $rate,
				'amount' => $amount,
			)
		);
	}
	
	function get_my_trade_history($currency_pair, $order_number) {
		
		return gate_query('1/private/tradeHistory',
			array(
				'currencyPair' => strtoupper($currency_pair),
				'orderNumber' => $order_number
			)
		);
	}
	
	function open_orders($currency_pair='') {
		
		return gate_query('1/private/openOrders',
		array(
				'currencyPair' => strtoupper($currency_pair)
			));
	}
	
	function deposites_withdrawals($start, $end) {
		
		return gate_query('1/private/depositsWithdrawals',
			array(
				'start' => $start,
				'end' => $end
			)
		);
	}
	
	function new_adddress($currency) {
		
		return gate_query('1/private/newAddress',
			array(
				'currency' => strtoupper($currency)
			)
		);
	}
	
	function deposit_address($currency) {
		
		return gate_query('1/private/depositAddress',
			array(
				'currency' => strtoupper($currency)
			)
		);
	}
	
	function check_username($username, $phone, $sign) {
		
		
		return gate_query('1/checkUsername',
			array(
				'username' => $username,
				'phone' => $phone,
				'sign' => $sign
			)
		);
	}
	
try {

/*** public API methods examples ***/

    // 所有交易对
    //	print_r( get_pairs());


    //交易市场订单参数
    //	print_r(get_marketinfo());

	//交易市场详细行情
    //    print_r(get_marketlist());

    // 所有交易行情
    //	print_r(get_tickers());

    //单项交易行情
    //	print_r(get_ticker('eth_btc'));

    //交易对的市场深度
    //    print_r(get_orderbooks());

    //指定交易对的市场深度
    //    print_r(get_orderbook('btc_usdt'));

    //历史成交记录
    //    print_r(get_trade_history('btc_usdt', 1000));


	
/*** private API methods examples ***/


    // 获取账号资金余额
//    print_r(get_balances());


    //获取充值地址
//    print_r(deposit_address('btc'));


    //获取充值提现历史
//    print_r(deposites_withdrawals('1469092370', '1670713981'));


    //下单交易买入
//    print_r(buy('etc_btc', '0.0035', '0.3'));

    //下单交易卖出
//    print_r(sell('etc_btc', '0.00214', '0.3'));


    //取消下单
//    print_r(cancel_order(263393711), 'etc_btc');


    //取消所有下单
    //    print_r(cancel_all_orders('0', 'etc_btc'));


    //获取下单状态
//    print_r(get_order(263393711, 'etc_btc'));


    //获取我的当前挂单列表
//    print_r(open_orders());


    //获取我的24小时内成交记录
//	print_r(get_trade_history('eth_btc',27817390));


    //提现
//	print_r(withdraw('btc','11','your wallet address'));


} catch (Exception $e) {
	echo "Error:".$e->getMessage();
} 
?>
