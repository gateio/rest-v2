#!/usr/bin/perl
#
# $Id: gateioApi.pl,v 0.01 2018/04/26 09:31:48 rob Exp $
#

package GateIO;

use Mojo::Base -base;
use Mojo::UserAgent;
use Digest::SHA qw(hmac_sha512_hex);

use constant API_QUERY => 'https://data.gateio.co';
use constant API_TRADE => 'https://api.gateio.co';
use constant {
    URL_PAIRS => '/api2/1/pairs',
    URL_MARKET_INFO => '/api2/1/marketinfo',
    URL_MARKET_LIST => '/api2/1/marketlist',
    URL_TICKERS => '/api2/1/tickers',
    URL_TICKER => '/api2/1/ticker',
    URL_ORDER_BOOKS => '/api2/1/orderBooks',
    URL_ORDER_BOOK => '/api2/1/orderBook',
    URL_TRADE_HISTORY => '/api2/1/tradeHistory',
    URL_CANDLESTICK => '/api2/1/candlestick2',
    URL_BALANCES => '/api2/1/private/balances',
    URL_DEPOSIT_ADDRESS => '/api2/1/private/depositAddress',
    URL_DEPOSITS_WITHDRAWALS => '/api2/1/private/depositsWithdrawals',
    URL_BUY => '/api2/1/private/buy',
    URL_SELL => '/api2/1/private/sell',
    URL_CANCEL_ORDER => '/api2/1/private/cancelOrder',
    URL_CANCEL_ORDERS => '/api2/1/private/cancelOrders',
    URL_CANCEL_ALL_ORDERS => '/api2/1/private/cancelAllOrders',
    URL_GET_ORDER => '/api2/1/private/getOrder',
    URL_OPEN_ORDERS => '/api2/1/private/openOrders',
    URL_MY_TRADE_HISTORY => '/api2/1/private/tradeHistory',
    URL_WITHDRAW => '/api2/1/private/withdraw',
};

has 'api_key';
has 'secret_key';
has ua => sub { Mojo::UserAgent->new };

# 所有交易对
sub pairs {
    shift->ua->get(API_QUERY . URL_PAIRS)->res->json;
}

# 市场订单参数
sub market_info {
    shift->ua->get(API_QUERY . URL_MARKET_LIST)->res->json;
}

# 交易市场详细行情
sub market_list {
    shift->ua->get(API_QUERY . URL_MARKET_LIST)->res->json;
}

# 所有交易行情
sub tickers {
    shift->ua->get(API_QUERY . URL_TICKERS)->res->json;
}

# 单项交易行情
sub ticker {
    my $self = shift;
    my $param = shift; # 交易对名称
    $self->ua->get(API_QUERY . URL_TICKER . '/' . $param)->res->json;
}

# 所有交易对市场深度
sub order_books {
    shift->ua->get(API_QUERY . URL_ORDER_BOOKS)->res->json;
}

# 单项交易对市场深度
sub order_book {
    my $self = shift;
    my $param = shift; # 交易对名称
    $self->ua->get(API_QUERY . URL_ORDER_BOOK . '/' . $param)->res->json;
}

# 历史成交记录
sub trade_history {
    my $self = shift;
    my $param = shift; # 交易对名称
    my $tid = shift;
    $param .= "/$tid" if defined $tid and $tid =~ /^\d+$/;
    $self->ua->get(API_QUERY . URL_TRADE_HISTORY . '/' . $param)->res->json;
}

# 交易市场K线数据
sub candlestick {
    my $self = shift;
    my $param = shift; # 交易对名称
    my $group_sec = shift;
    my $rang_hour = shift;

    my $q = "/$param";
    if (defined $group_sec and defined $rang_hour) {
        $q .= "?group_sec=$group_sec&rang_hour=$rang_hour";
    }
    $self->ua->get(API_QUERY . URL_TRADE_HISTORY . $q)->res->json;
}

# 获取帐号资金余额
sub balances {
    my $self = shift;
    my $params = {};
    return $self->ua->post((API_TRADE . URL_BALANCES)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub deposit_address {
    my $self = shift;
    my $params = { @_ == 1 ? ('currency' => shift) : @_ };
    return $self->ua->post((API_TRADE . URL_DEPOSIT_ADDRESS)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub deposits_withdrawals {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_DEPOSITS_WITHDRAWALS)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub buy {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_BUY)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub sell {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_SELL)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub cancel_order {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_CANCEL_ORDER)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub cancel_orders {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_CANCEL_ORDERS)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub cancel_all_orders {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_CANCEL_ALL_ORDERS)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub get_order {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_GET_ORDER)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub open_orders {
    my $self = shift;
    my $params = {};
    return $self->ua->post((API_TRADE . URL_OPEN_ORDERS)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub my_trade_history {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_MY_TRADE_HISTORY)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub withdraw {
    my $self = shift;
    my $params = { @_ };
    return $self->ua->post((API_TRADE . URL_WITHDRAW)
            => { KEY => $self->api_key, SIGN => $self->build_sign($params) }
            => form => $params
           )->res->json;
}

sub build_sign {
    my $self = shift;
    my $params = shift;
    my $sign = '';
    
    for my $k ( sort(keys %$params) ) {
        $sign .= $k . '=' . $params->{$k} .'&';
    }
    $sign =~ s/&$//;

    return hmac_sha512_hex($sign, $self->secret_key);
}


package main;

use strict;
use Data::Dumper;

# 设置API Key，Secret Key
my $api_key = 'your api key';
my $secret_key = 'your secret key';

my $api = GateIO->new;

# Setting proxy
# my $proxy = 'socks://127.0.0.1:2080/';
# $api->ua->proxy->http($proxy)->https($proxy);

say Dumper( $api->pairs );
say Dumper( $api->ticker('btc_usdt') );


$api->api_key($api_key);
$api->secret_key($secret_key);

say Dumper( $api->balances );
say Dumper( $api->my_trade_history(currencyPair => 'btc_usdt') );


1;
