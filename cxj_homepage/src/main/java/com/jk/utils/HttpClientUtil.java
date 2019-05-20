package com.jk.utils;
 
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
	
	static CloseableHttpClient client = null;
	static {
		//1、创建HttpClient实例
		client = HttpClients.createDefault();
	}
	

	//方法: get  调接口，需要url 和  各种参数，参数不确定有几个所以放到了 map 里
	public static String get(String url,HashMap<String, Object> params){
		try {
			//2、创建HttpGet方法请求
			HttpGet httpGet = new HttpGet();
			Set<String> keySet = params.keySet();//把map里的值都拿出来
			StringBuffer stringBuffer = new StringBuffer();//拼接字符串用
			//api 的前面格式
			stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());//每次请求的值加上时间戳。确保不一致
			 //继续拼接具体参数，不确定所以循环
			for (String key : keySet) {
				stringBuffer.append("&").append(key).append("=").append(params.get(key));
			}
			   //把拼接的URL 转换为String 格式，放到该方法的URL 属性里
			httpGet.setURI(new URI(stringBuffer.toString()));
			//3、调用execute方法执行
			CloseableHttpResponse execute = client.execute(httpGet);//调用方法执行这个请求
			//4、获取响应的状态码，判断请求是否成功
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			//5、获取请求成功的信息
			return EntityUtils.toString(execute.getEntity(), "utf-8");
			
		}catch (Exception e) {
			//6、关闭资源
			e.printStackTrace();
			
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	//描述: post请求 	 
	public static String post(String url,HashMap<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			 //post请求底层是KEy value 形式，所以 new 了个Arraylist
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
				parameters.add(e);
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			//6、关闭资源
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	
	//意思意思  没用
	public static void main(String[] args) {
		  //这两步是给get请求赋值。1.是 接口 url 
		String url = "http://t.weather.sojson.com/api/weather/city/101010100";
			//2.是传递的参数，因为这里直接吧参数跟在了url后，所以不需要参数，给个空就好
		HashMap<String, Object> params = new HashMap<>();
		 //1.确定请求方式  返回的是String
		String returnStr = get(url, params);
		  //在转换为JSON对象
		JSONObject parseObject = JSON.parseObject(returnStr);
		int status = parseObject.getIntValue("status");//给返回是否请求成功。（状态码）
		  //请求成功或者失败
		if(status!=200){
			System.out.println("请求失败");
		}else{
			 //转换json对象，因为返回的data数据是 json对象格式{[ ],[ ]}
			JSONObject data = parseObject.getJSONObject("data");
				//data里的数据时json数组格式   json对象里的数据  又是  数组格式  [ ]
			JSONArray forecast = data.getJSONArray("forecast");
				//取出最高温的 数据  又是 JSON 对象格式 key value 格式 
			JSONObject jsonObject = forecast.getJSONObject(0);
			String high = jsonObject.getString("high");
			System.out.println("今天的最高温度：==="+high);
		}
	}
}
