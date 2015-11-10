package ief.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Http请求工具类
 * 
 * @author hgliu
 *
 */
public class HttpUtil {
	
	public static final String BAIDU_GET_DIS= "http://api.map.baidu.com/geocoder/v2/?ak=F7f4e9ffd4170d51d05ffe0e69478e3e&output=json&pois=1&location=";
	
	private static final Logger logger = Logger.getLogger(HttpUtil.class);

	private static final int MAX_TOTAL_CONNECTIONS = 500;
	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int SOCKET_TIMEOUT = 5000;
	private static final int CONNECTION_MANAGER_TIMEOUT = 2000;

	private static CloseableHttpClient client;
	private static RequestConfig requestConfig;

	private static ExecutorService executorService;

	private static final int corePoolSize = 0;
	private static final int maximumPoolSize = 10000;
	private static final int queueCapacity = 10000;
	private static final int keepAliveTime = 10;

	private static final String httpPostErr = "HTTP POST 请求发生异常:";

	public static String getFullURL(HttpServletRequest request) {
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();

		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

	public static void logRequest(Log logger, String requestName, HttpServletRequest request) {
		Enumeration e = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			sb.append(name + ": " + request.getParameter(name) + " ");
		}
		logger.info(request.toString() + " " + requestName + ": " + sb.toString());
	}

	static {
		ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultConnectionConfig(connectionConfig);
		connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT)
				.setConnectionRequestTimeout(CONNECTION_MANAGER_TIMEOUT).build();
		client = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
		executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(queueCapacity), new ThreadPoolExecutor.CallerRunsPolicy());
	}

	/**
	 * Get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		HttpGet method = new HttpGet(url);
		try {
			CloseableHttpResponse response = client.execute(method);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return EntityUtils.toString(response.getEntity());
				}
				logger.info("http get:" + response.getStatusLine());
			} finally {
				response.close();
			}
		} finally {
			method.releaseConnection();
		}
		return HttpStatus.SC_INTERNAL_SERVER_ERROR + "";
	}

	/**
	 * Post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, NameValuePair... params) {
		HttpPost method = new HttpPost(url);
		try {
			if (params != null) {
				method.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), Consts.UTF_8));
			}
			CloseableHttpResponse response = client.execute(method);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return EntityUtils.toString(response.getEntity());
				}
			} finally {
				response.close();
			}
			logger.info("http post:" + response.getStatusLine());
		} catch (IOException e) {
			logger.error(httpPostErr + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
		return HttpStatus.SC_INTERNAL_SERVER_ERROR + "";
	}

	public static String postXML(String url, String body) {
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader("Content-Type", "text/xml");
			method.setEntity(new StringEntity(body, Consts.UTF_8));
			CloseableHttpResponse response = client.execute(method);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return EntityUtils.toString(response.getEntity());
				}
			} finally {
				response.close();
			}
			logger.info("http post:" + response.getStatusLine());
		} catch (IOException e) {
			logger.error(httpPostErr + e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
		return HttpStatus.SC_INTERNAL_SERVER_ERROR + "";
	}

	public static Future<String> postAsyn(final String url, final NameValuePair... params) {
		return executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return post(url, params);
			}
		});
	}

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		String res = post("http://localhost:8280/broadcast/cCurrency.do", new BasicNameValuePair("userId", "1"));
		Future<String> future = postAsyn("http://localhost:8280/broadcast/cCurrency.do",
				new BasicNameValuePair("userId", "4"));
		System.out.println("post asyn");
		System.out.println(res);
		System.out.println(future.get());
	}
}
