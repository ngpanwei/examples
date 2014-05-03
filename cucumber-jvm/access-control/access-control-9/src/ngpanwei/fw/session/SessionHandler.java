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
package ngpanwei.fw.session;

import java.util.HashMap;

import ngpanwei.access.admin.IUserDataAccess;
import ngpanwei.access.admin.UserAccount;
import ngpanwei.access.admin.UserInfo;
import ngpanwei.fw.AppException;
import ngpanwei.fw.ObjectFactory;

/**
 * This class provides session control from logging in 
 * to tracking session info to logging out.
 * @author ngpanwei
 */
public class SessionHandler implements ISessionHandler {
	HashMap<Thread,Session> sessionPool = null ;
	public SessionHandler() {
		reset() ;
	}
	public void reset() {
		sessionPool = new HashMap<Thread,Session>() ;
	}
	
	public Session getSession() {
		Session session = sessionPool.get(Thread.currentThread()) ;
		if(session==null) {
			session = new Session() ;
			sessionPool.put(Thread.currentThread(), session) ;
		}
		return session ;
	}

}
