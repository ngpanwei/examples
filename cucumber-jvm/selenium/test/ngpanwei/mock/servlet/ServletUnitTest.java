/** 
  *  Copyright (c) 2014  Ng Pan Wei
  *  
  *  Permission is hereby granted, free of charge, to any person 
  *  obtaining a copy of this software and associated documentation files 
  *  (the "Software"), to deal in the Software without restriction, 
  *  including without limitation the rights to use, copy, modify, merge, 
  *  publish, distribute, sublicense, and/or sell copies of the Software, 
  *  and to permit persons to whom the Software is furnished to do so, 
  *  subject to the following conditions: 
  *  
  *  The above copyright notice and this permission notice shall be 
  *  included in all copies or substantial portions of the Software. 
  *  
  *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
  *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
  *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
  *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
  *  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
  *  ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
  *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
  *  SOFTWARE. 
  */ 
package ngpanwei.mock.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ngpanwei.mock.framework.DynamicProxy;

/**
 * Servlet test runtime environment.
 * @author ngpanwei
 */
public class ServletUnitTest {
	public HttpSessionHandler sessionH ;
	public HttpSession session ;
	protected HttpServletRequestHandler requestH ;
	protected HttpServletRequest request ;
	protected HttpServletResponseHandler responseH ;
	protected HttpServletResponse response ;
	protected ServletContext context ;
	protected ServletContextHandler contextH ;
	protected ServletConfig config ;
	protected ServletConfigHandler configH ;
	public RequestDispatcherHandler requestDispatcherH ;
	public RequestDispatcher requestDispatcher ;
	
	/**
	 * Constructor.
	 */
	public ServletUnitTest() {
		reset();		
	}

	/**
	 * re-initialize and connect all objects within the 
	 * servlet runtime environment.
	 */
	public void reset() {
		sessionH = new HttpSessionHandler() ;
		session = (HttpSession)DynamicProxy
					.newProxy(HttpSession.class, sessionH) ;

		requestH = new HttpServletRequestHandler() ;
		request = (HttpServletRequest)DynamicProxy
					.newProxy(HttpServletRequest.class, requestH) ;	
		requestH.session = session ;

		responseH = new HttpServletResponseHandler() ;
		response = (HttpServletResponse)DynamicProxy
					.newProxy(HttpServletResponse.class, responseH) ;

		contextH = new ServletContextHandler() ;
		context = (ServletContext)DynamicProxy
					.newProxy(ServletContext.class, contextH) ;
		
		configH = new ServletConfigHandler() ;
		config = (ServletConfig)DynamicProxy
					.newProxy(ServletConfig.class, configH) ;
		// configH.setServletContext(context);

		requestDispatcherH = new RequestDispatcherHandler() ;
		requestDispatcher = (RequestDispatcher)DynamicProxy
					.newProxy(RequestDispatcher.class, requestDispatcherH) ;
		contextH.requestDispatcher = requestDispatcher ;
	}
}
