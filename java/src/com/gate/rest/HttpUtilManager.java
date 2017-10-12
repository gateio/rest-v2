package com.gate.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;



public class HttpUtilManager {
	
	private static String SECRET = "your secret";
	private static String KEY = "your key";
	
	private static HttpUtilManager instance = new HttpUtilManager();
	private static HttpClient client;
	private static long startTime = System.currentTimeMillis();
	public  static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();  
	private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {  

	     public long getKeepAliveDuration(  
	            HttpResponse response,  
	            HttpContext context) {  
	        long keepAlive = super.getKeepAliveDuration(response, context);  
	        
	        if (keepAlive == -1) {  
	            keepAlive = 5000;  
	        }  
	        return keepAlive;  
	    }  
	  
	};
	private HttpUtilManager() {
		client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build(); 
	}

    public static void IdleConnectionMonitor(){
		
		if(System.currentTimeMillis()-startTime>30000){
			 startTime = System.currentTimeMillis();
			 cm.closeExpiredConnections();  
             cm.closeIdleConnections(30, TimeUnit.SECONDS);
		}
	}
	 
	private static RequestConfig requestConfig = RequestConfig.custom()
	        .setSocketTimeout(20000)
	        .setConnectTimeout(20000)
	        .setConnectionRequestTimeout(20000)
	        .build();
	
	
	public static HttpUtilManager getInstance() {
		return instance;
	}

	public HttpClient getHttpClient() {
		return client;
	}

	private HttpPost httpPostMethod(String url) {
		return new HttpPost(url);
	}

	private  HttpRequestBase httpGetMethod(String url) {
		return new  HttpGet(url);
	}
	
	public String requestHttpGet(String url_prex,String url,String param) throws HttpException, IOException{
		
		IdleConnectionMonitor();
		url=url_prex+url;
		if(param!=null && !param.equals("")){
		        if(url.endsWith("?")){
			    url = url+param;
			}else{
			    url = url+"?"+param;
			}
		}
		HttpRequestBase method = this.httpGetMethod(url);
		method.setConfig(requestConfig);
		HttpResponse response = client.execute(method);
		HttpEntity entity =  response.getEntity();
		if(entity == null){
			return "";
		}
		InputStream is = null;
		String responseData = "";
		try{
		    is = entity.getContent();
		    responseData = IOUtils.toString(is, "UTF-8");
		}finally{
			if(is!=null){
			    is.close();
			}
		}
		return responseData;
	}
	
	public String requestHttpPost(String url_prex,String url,Map<String,String> params) throws HttpException, IOException{
		
		IdleConnectionMonitor();
		url=url_prex+url;
		

		HttpPost method = this.httpPostMethod(url);
		List<NameValuePair> valuePairs = this.convertMap2PostParams(params);


		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
		method.setEntity(urlEncodedFormEntity);
		method.setConfig(requestConfig);
		System.out.println(method);
		HttpResponse response = client.execute(method);
		HttpEntity entity =  response.getEntity();
		if(entity == null){
			return "";
		}
		InputStream is = null;
		String responseData = "";
		try{
		    is = entity.getContent();
		    responseData = IOUtils.toString(is, "UTF-8");
		}finally{
			if(is!=null){
			    is.close();
			}
		}
		return responseData;
		
	}
	
	private List<NameValuePair> convertMap2PostParams(Map<String,String> params){
		List<String> keys = new ArrayList<String>(params.keySet());
		if(keys.isEmpty()){
			return null;
		}
		int keySize = keys.size();
		List<NameValuePair>  data = new LinkedList<NameValuePair>() ;
		for(int i=0;i<keySize;i++){
			String key = keys.get(i);
			String value = params.get(key);
			data.add(new BasicNameValuePair(key,value));
		}
		return data;
	}
	
	
	public String doRequest( String api, String requestType, String url, Map< String, String > arguments )  throws HttpException, IOException{

		List< NameValuePair > urlParameters = new ArrayList< NameValuePair >( );

		Mac mac = null;
		SecretKeySpec key = null;

		String postData = "";

		for ( Iterator< Entry< String, String >> argumentIterator = arguments.entrySet( ).iterator( ); argumentIterator.hasNext( ); ) {

			Map.Entry< String, String > argument = argumentIterator.next( );

			urlParameters.add( new BasicNameValuePair( argument.getKey( ).toString( ), argument.getValue( ).toString( ) ) );

			if ( postData.length( ) > 0 ) {
				postData += "&";
			}

			postData += argument.getKey( ) + "=" + argument.getValue( );

		}

		// Create a new secret key
		try {
			key = new SecretKeySpec( SECRET.getBytes( "UTF-8" ), "HmacSHA512" );
		} catch ( UnsupportedEncodingException uee ) {
			System.err.println( "Unsupported encoding exception: " + uee.toString( ) );
		}

		try {
			mac = Mac.getInstance( "HmacSHA512" );
		} catch ( NoSuchAlgorithmException nsae ) {
			System.err.println( "No such algorithm exception: " + nsae.toString( ) );
		}

		try {
			mac.init( key );
		} catch ( InvalidKeyException ike ) {
			System.err.println( "Invalid key exception: " + ike.toString( ) );
		}

		// add header
		Header[] headers = new Header[ 2 ];
		headers[ 0 ] = new BasicHeader( "Key", KEY );
		headers[ 1 ] = new BasicHeader( "Sign", Hex.encodeHexString( mac.doFinal( postData.getBytes( "UTF-8" ) ) ) );


		HttpClient client = HttpClientBuilder.create( ).build( );
		HttpPost post = null;
		HttpGet get = null;
		HttpResponse response = null;

		if ( requestType == "post" ) {
			post = new HttpPost( url );
			post.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			post.setHeaders( headers );
			response = client.execute( post );
		} else if ( requestType == "get" ) {
			get = new HttpGet( url );
			get.setHeaders( headers );
			response = client.execute( get );
		}
		
		HttpEntity entity =  response.getEntity();
		if(entity == null){
			return "";
		}
		InputStream is = null;
		String responseData = "";
		
	    is = entity.getContent();
	    responseData = IOUtils.toString(is, "UTF-8");
		return responseData;
		

	}

}

