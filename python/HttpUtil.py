#!/usr/bin/python
# -*- coding: utf-8 -*-

import http.client
import urllib
import json
from hashlib import sha512
import hmac

def getSign(params,secretKey):
    sign = ''
    for key in (params.keys()):
        sign += key + '=' + str(params[key]) +'&'
    sign = sign[:-1]
    my_sign = hmac.new( bytes(secretKey,encoding='utf8'),bytes(sign,encoding='utf8'), sha512).hexdigest()
    return my_sign


def httpGet(url,resource,params=''):
    conn = http.client.HTTPSConnection(url, timeout=10)
    conn.request("GET",resource + '/' + params )
    response = conn.getresponse()
    data = response.read().decode('utf-8')
    return json.loads(data)

def httpPost(url,resource,params,apikey,secretkey):
     headers = {
            "Content-type" : "application/x-www-form-urlencoded",
            "KEY":apikey,
            "SIGN":getSign(params,secretkey)
     }
     conn = http.client.HTTPSConnection(url, timeout=10)
     if params:
         temp_params = urllib.parse.urlencode(params)
     else:
         temp_params = ''
     print(temp_params)
     conn.request("POST", resource, temp_params, headers)
     response = conn.getresponse()
     data = response.read().decode('utf-8')
     params.clear()
     conn.close()
     return data


        
     
