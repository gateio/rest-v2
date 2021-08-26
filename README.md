# Rest API Introduction

Welcome to the Gate Rest API! You can use this API to get market data, trade, and manage your account.

Each API call is described in detail in the [API2.0](https://gate.io/en/api2) English documentation

The API v2 version is no longer being updated and maintained, and we recommend that you use the latest [API v4](https://www.gate.io/docs/developers/apiv4/en/) version.

## API Interface Address

https://data.gateapi.io
    
    * Access via proxy is not recommended for reasons such as high latency and poor stability.

 
## Rate Limit
  10r/s
  
## Create API Key
You can create the API Key at [here](https://www.gate.io/en/myaccount/apikeys).

API Keys includes the following two parts

Key : API key

Secret : The key used for signature authentication encryption

Write the requested API keys to the application configuration file.

    // API settings, add your Key and Secret at here
    $key = '';
    $secret = '';


    *When creating API Keys, you can choose to bind IPs, which can be set by IP whitelist (multiple IPs separated by half comma).
    *Note: Using the API Keys above will allow you to perform account information inquiries and trading operations through the program, but not withdrawal operations. Do not disclose the API Keys to others.
    *Create new API Keys: Generate new API Keys, the old ones will be invalidated immediately.

## Request Format 
   All API requests are called as GET or POST. Getting market data is requested via GET, with all parameters in path parameters; for transaction and account data is requested via POST, and all parameters are sent in JSON format.
 
## Return Format
   All interface returns are in JSON format. The [API2.0](https://gate.io/en/api2) document has JSON fields for request status and properties.
 
## Error message
 
  The system returns an error code corresponding to the description
 
    Error Codes	       Details
    1	               Invalid request
    2	               Invalid version
    3	               Invalid request
    4	               Forbidden access
    5,6	               Invalid sign
    7	               Currency is not supported
    8,9	               Currency is not supported
    10	               Verified failed
    11	               Obtaining address failed
    12	               Empty params
    13	               Internal error, please report to administrator
    14	               Invalid user
    15	               Cancel order too fast, please wait 1 min and try again
    16	               Invalid order id or order is already closed
    17	               Invalid orderid
    18	               Invalid amount
    19	               Not permitted or trade is disabled
    20	               Your order size is too small
    21	               You don't have enough fund
    40	               Too many attempts

