package ief.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ief.dto.results.BaseResult;
import ief.enums.StatusEnum;
import ief.utils.ControllerUtil;
import ief.utils.JsonUtil;
import ief.utils.SecretUtil;

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	private static String[] NOAUTHURI={"/ief/app/list_books","/ief/app/get_book_detail","/ief/app/get_same_books"
			,"/ief/hello","/ief/app/register","/ief/app/login"};
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;  
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;  
		String uri=httpRequest.getRequestURI();
		if(isNoAuth(uri)){//不做权限认证
			chain.doFilter(request, response);
		}else{//认证
			String sessionId=httpRequest.getParameter("sessionId");
			sessionId="TxD18i9uw+32eI8md18XymTfuinz5V8FsUgunjNo3HPsUYTIAHc5ITtv7vOLqSjO";
			System.out.println(sessionId);
			System.out.println(httpRequest.getParameter("deviceId"));
			if(sessionId!=null){
				String decode=SecretUtil.decrypt(sessionId, SecretUtil.AUTHPASSWORD);
				int offset=decode.lastIndexOf('|');
				String deviceId=decode.substring(0, offset);
				String userId=decode.substring(offset+1,decode.length() );
				if(deviceId.equals(httpRequest.getParameter("deviceId"))&&userId.equals(httpRequest.getParameter("userId")))
					chain.doFilter(request, response);
				else{
					BaseResult baseResult =new BaseResult(StatusEnum.AUTH_ERR);
					ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
					return;
				}
			}else{
				BaseResult baseResult =new BaseResult(StatusEnum.AUTH_ERR);
				ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
				return;
			}
		}
	}
	/**
	 * 判断是否需要认证
	 * @param uri
	 * @return
	 */
	private boolean isNoAuth(String uri){
		for(String noAuth:NOAUTHURI){
			if(uri.startsWith(noAuth)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void destroy() {
		
	}
	public static void main(String[] args) {
		String test="12345678|9";
		int offset=test.lastIndexOf('|');
		String deviceId=test.substring(0, offset);
		String userId=test.substring(offset+1,test.length() );
		System.out.println(offset+"\t"+deviceId+"\t"+userId);
	}
}
