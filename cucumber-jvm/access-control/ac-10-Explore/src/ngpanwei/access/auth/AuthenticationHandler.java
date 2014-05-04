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
package ngpanwei.access.auth;

import ngpanwei.access.admin.IUserDataAccess;
import ngpanwei.access.admin.UserAccount;
import ngpanwei.access.admin.UserInfo;
import ngpanwei.fw.AppException;
import ngpanwei.fw.ObjectFactory;
import ngpanwei.fw.session.ISessionHandler;
import ngpanwei.fw.session.Session;

public class AuthenticationHandler implements IAuthenticationHandler {
	@Override
	public void login(String username, String password) throws AppException {
		IUserDataAccess userDataAccess = (IUserDataAccess) ObjectFactory.getInstance(IUserDataAccess.class) ;
		if(userDataAccess==null) {
			throw new AppException(IUserDataAccess.class.getSimpleName() + " not initialized.") ;
		}
		UserInfo userInfo = new UserInfo() ;
		userInfo.put(UserInfo.USERNAME, username) ;
		UserAccount account = userDataAccess.readUser(userInfo) ;
		String accountPassword = account.get(UserInfo.PASSWORD) ;
		if(accountPassword.equals(password)==false) {
			throw new AppException(IAuthenticationHandler.WrongPassword + "for user "+username) ;
		}
		ISessionHandler sessionHandler = (ISessionHandler)ObjectFactory.getInstance(ISessionHandler.class) ;
		Session session = sessionHandler.getSession() ;
		session.put(UserInfo.USERNAME,account.get(UserInfo.USERNAME)) ;
	}
}
