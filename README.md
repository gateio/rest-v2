# Rest API 简介

欢迎使用Gate Rest API！ 你可以使用此 API 获得市场行情数据，交易，并且管理你的账户。

具体每一个API调用，[API2.0](https://gateio.co/api2)中文文档 有详细说明

## API接口地址

https://data.gateio.co
    
    * 鉴于高延迟和差稳定性等原因，不建议通过代理方式访问。
 
## 限频规则
  10次/秒
  
## 创建 API Key
您可以在 [这里](https://www.gateio.co/myaccount/apikeys) 创建 API Key。

API Keys 包括以下两部分

Key 密钥

Secret 签名认证加密所使用的密钥

把申请到的API keys 写入到程序配置文件中。

    // API settings, add your Key and Secret at here
    $key = '';
    $secret = '';


    *创建 API Keys 时可以选择绑定 IP ,可通过IP 白名单(多个IP用英文半角逗号隔开) 设置。
    *注意：使用上面的 API Keys 可以让您通过程序进行账号信息查询，交易操作，不能进行提现操作。切勿泄露API Keys给他人。
    *创建新的API Keys：产生新的API Keys，旧的API Keys将立即失效。

 ##请求格式
 所有的API请求都以GET或者POST形式调用。获取市场数据通过GET请求，所有的参数都在路径参数里；对于交易和账户数据通过POST请求，所有参数则以JSON格式发送。
 
 ##返回格式
 所有的接口返回都是JSON格式。在[API2.0](https://gateio.co/api2) 文档中有JSON表示请求状态和属性的字段。
 
 ## 错误信息
 
 系统返回错误码对应说明
 
    错误代码	详细描述
    1	无效请求
    2	无效版本
    3	无效请求
    4	没有访问权限
    5,6	Key或签名无效，请重新创建
    7	币种对不支持
    8,9	币种不支持
    10	验证错误
    11	地址获取失败
    12	参数为空
    13	系统错误，联系管理员
    14	无效用户
    15	撤单太频繁，一分钟后再试
    16	无效单号，或挂单已撤销
    17	无效单号
    18	无效挂单量
    19	交易已暂停
    20	挂单量太小
    21	资金不足
    40	请求太频繁，稍后再试

