var gate = require('./lib/gate');

// 获取所有交易对
gate.getPairs(function (res) {
    console.log(res);
});


//交易市场订单参数
// gate.getMarketinfo(function (res) {
//     console.log(res);
// });


//交易市场详细行情
// gate.getMarketlist(function (res) {
//     console.log(res);
// });


//所有交易行情
// gate.getTickers(function (res) {
//     console.log(res);
// });


//单项交易行情
// gate.getTicker('eth_btc',function (res) {
//     console.log(res);
// });


// 市场深度
// gate.orderBooks(function (res) {
//     console.log(res);
// });


// 指定市场深度
// gate.orderBook('etc_btc',function (res) {
//     console.log(res);
// });


// 历史成交记录
// gate.tradeHistory('etc_btc',function (res) {
//     console.log(res);
// });


// 获取资金余额
// gate.getBalances(function (res) {
//     console.log(res);
// });


// 获取充值地址
// gate.depositAddress('btc',function (res) {
//     console.log(res);
// });


// 获取充值提现历史
// gate.depositsWithdrawals('1508225535','1508311935',function (res) {
//     console.log(res);
// });

// 下单交易买入
// gate.buy('etc_btc','0.001',	'0.876',function (res) {
//     console.log(res);
// });


// 下单交易卖出
// gate.sell('etc_btc','0.02','3',function (res) {
//     console.log(res);
// });

//取消下单
// gate.cancelOrder('267040896','etc_btc', function (res) {
//     console.log(res);
// });

// 取消所有下单
// gate.cancelAllOrders('1', 'etc_btc', function (res) {
//     console.log(res);
// });

//获取下单状态
// gate.getOrder('267040896', 'etc_btc', function (res) {
//     console.log(res);
// });


//获取我的当前挂单列表
// gate.openOrders( function (res) {
//     console.log(res);
// });


//获取我的24小时内成交记录
// gate.myTradeHistory('etc_btc','267040896', function (res) {
//     console.log(res);
// });


// 提现
// gate.withdraw('btc','123', '1CGJ7kWYC8fHYShC4m8Wy9j9BfrD9fLn58', function (res) {
//     console.log(res);
// });
