using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Web;
using System.Net;
using Newtonsoft.Json.Linq;
using System.Security.Cryptography;

namespace Com.Gate.Rest
{
    class HttpUtilManager
    {
        private static String SECRET = ""; //您的API Secret
        private static String KEY = ""; //您的API Key
        private static HttpUtilManager instance = new HttpUtilManager();
        private HttpUtilManager() { }
        public static HttpUtilManager getInstance()
        {
            return instance;
        }

        String result = "";

        //请求类
        HttpWebRequest request = null;
        //请求响应类
        HttpWebResponse response = null;
        //响应结果读取类
        StreamReader reader = null;

        public String requestHttpGet(String url_prex, String url, String param)
        {
            url = url_prex + url;
            if (param == null || !"".Equals(url))   
            {
                if (url.EndsWith("?"))
                {
                    url = url + param;
                }
                else
                {
                    url = url + "?" + param;
                }
            }
            //http连接数限制默认为2，多线程情况下可以增加该连接数，非多线程情况下可以注释掉此行代码
            //ServicePointManager.DefaultConnectionLimit = 500;
            request = WebRequest.Create(url) as HttpWebRequest;
            request.ProtocolVersion = HttpVersion.Version10;
            request.Method = "GET";
            request.Timeout = 30000;
            response = (HttpWebResponse)request.GetResponse();
            reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
            result = reader.ReadToEnd();
            return result;
        }

        public String doRequest(String api, String requestType, String url, Dictionary<String, String> arguments)
        {
            string result = "";
            String postData = "";
            if (arguments.Count > 0)
            {
                foreach (var str in arguments)
                {
                    if (postData.Length > 0)
                    {
                        postData += "&";
                    }
                    postData += str.Key + "=" + str.Value;
                }
            }
            request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = requestType;
            request.ContentType = "application/x-www-form-urlencoded";
            request.Headers.Add("Key", KEY);
            request.Headers.Add("Sign", (String)GetHMACSHA512.hash_hmac(postData, SECRET));
            StringBuilder builder = new StringBuilder();
            int i = 0;
            foreach (var item in arguments)
            {
                if (i > 0)
                    builder.Append("&");
                builder.AppendFormat("{0}={1}", item.Key, item.Value);
                i++;
            }
            byte[] data = Encoding.UTF8.GetBytes(builder.ToString());
            request.ContentLength = data.Length;
            using (Stream reqStream = request.GetRequestStream())
            {
                reqStream.Write(data, 0, data.Length);
                reqStream.Close();
            }
            HttpWebResponse resp = (HttpWebResponse)request.GetResponse();
            Stream stream = resp.GetResponseStream();
            //获取响应内容
            using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
            {
                result = reader.ReadToEnd();
            }
            return result;

        }
    }
}
