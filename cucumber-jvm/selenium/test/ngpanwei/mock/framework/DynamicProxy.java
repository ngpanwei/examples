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
package ngpanwei.mock.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * Class to generate dynamic proxies for a given interface.
 * @author ngpanwei
 */
public class DynamicProxy {
    /**
     * Create a proxy for target interface with given handler.
     * @param clazz
     * @param handler
     * @return
     */
	public static Object newProxy(Class<?> clazz,ProxyHandler handler) {
		Object proxy = newProxy( Thread.currentThread().getContextClassLoader(),clazz,handler) ;
        return proxy ;
    }
    /**
     * Create a proxy for target interface.
     * @param clazz
     * @return
     */
	private static Object newProxy(ClassLoader pClassLoader, final Class<?> clazz, final ProxyHandler handler)
    {
    	Object object = Proxy.newProxyInstance(pClassLoader, new Class[] { clazz }, 
        	new InvocationHandler() {
            	public Object invoke(Object proxy, Method method,
                    Object[] args) throws Throwable {
            		try {
            			return handler.handleInvocation(method, args) ;
            		} catch (DynamicProxyRuntimeException e) {
            			throw new DynamicProxyRuntimeException("Undefined "
            					+ handler.getClass().getSimpleName()
            					+ "::" + method.getName()) ;
            		}
            	}
            });
    	return object ;
    }
}
