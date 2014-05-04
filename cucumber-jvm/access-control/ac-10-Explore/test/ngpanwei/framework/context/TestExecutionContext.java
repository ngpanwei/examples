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
package ngpanwei.framework.context;

import ngpanwei.access.admin.IUserAdminHandler;
import ngpanwei.access.admin.IUserDataAccess;
import ngpanwei.access.admin.UserAdminHandler;
import ngpanwei.access.admin.UserDataAccess;
import ngpanwei.access.auth.AuthenticationHandler;
import ngpanwei.access.auth.IAuthenticationHandler;
import ngpanwei.fw.ObjectFactory;
import ngpanwei.fw.session.ISessionHandler;
import ngpanwei.fw.session.SessionHandler;

/**
 * Test Execution Context
 * @author ngpanwei
 */
public class TestExecutionContext {
	private static boolean initFlag = false ;
	public static void init() {
		if(initFlag==false) {
			setContext() ;
			initFlag = true ;
		}
	}
	protected static void setContext() {
		ObjectFactory.setInstance(IUserDataAccess.class, new UserDataAccess());
		ObjectFactory.setInstance(IUserAdminHandler.class, new UserAdminHandler());
		ObjectFactory.setInstance(ISessionHandler.class, new SessionHandler());
		ObjectFactory.setInstance(IAuthenticationHandler.class, new AuthenticationHandler());
	}
}
