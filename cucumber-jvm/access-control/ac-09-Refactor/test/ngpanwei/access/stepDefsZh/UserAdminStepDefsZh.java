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
package ngpanwei.access.stepDefsZh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ngpanwei.access.admin.IUserAdminHandler;
import ngpanwei.access.admin.IUserDataAccess;
import ngpanwei.access.admin.UserAccount;
import ngpanwei.access.admin.UserInfo;
import ngpanwei.access.auth.IAuthenticationHandler;
import ngpanwei.framework.context.TestExecutionContext;
import ngpanwei.framework.intention.Intention;
import ngpanwei.fw.ObjectFactory;
import ngpanwei.fw.session.ISessionHandler;
import ngpanwei.fw.session.Session;
import cucumber.api.java.Before;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;

public class UserAdminStepDefsZh {
	Intention userIntent ;
	Session userSession ;
	IUserDataAccess userDataAccess ;
	IUserAdminHandler adminHandler ;
	IAuthenticationHandler authenticationHandler ;
	ISessionHandler sessionHandler ;
	/**
	 * 场景运行前的准备。
	 */
	@Before 
	public void setup() {
		TestExecutionContext.init() ;
		userDataAccess = (IUserDataAccess)ObjectFactory.getInstance(IUserDataAccess.class) ;
		userDataAccess.reset() ;
		adminHandler = (IUserAdminHandler)ObjectFactory.getInstance(IUserAdminHandler.class) ;
		authenticationHandler = (IAuthenticationHandler)ObjectFactory.getInstance(IAuthenticationHandler.class) ;
		sessionHandler = (ISessionHandler)ObjectFactory.getInstance(ISessionHandler.class) ;
		userIntent = new Intention() ;
		userSession = sessionHandler.getSession()  ;
	}
	@假如("^管理员新增用户 \"(.*?)\" 邮箱 \"(.*?)\"$")
	public void 管理员有一些新用户_称为_邮箱(String username, String email) throws Throwable {
		UserInfo userInfo = new UserInfo() ;
		userInfo.put(UserInfo.USERNAME, username) ;
		userInfo.put(UserInfo.USEREMAIL, email) ;
		UserAccount userAccount = adminHandler.createUser(userInfo) ;
		assertNotNull("User Account should not be null",userAccount) ;
	}

	@假如("^用户 \"(.*?)\" 把密码设为 \"(.*?)\"$")
	public void 用户_把密码设为(String username, String password) throws Throwable {
		UserInfo userInfo = new UserInfo() ;
		userInfo.put(UserInfo.USERNAME, username) ;
		userInfo.put(UserInfo.PASSWORD, password) ;
		UserAccount userAccount = adminHandler.changePassword(userInfo) ;
		assertNotNull("User Account should not be null",userAccount) ;

		userIntent.put(UserInfo.USERNAME, username) ;
		userIntent.put(UserInfo.PASSWORD, password) ;

	}

	@当("^用户 \"(.*?)\" 登入$")
	public void 用户_登入(String username) throws Throwable {
		authenticationHandler.login(username, userIntent.get(UserInfo.PASSWORD)) ;
	}

	@那么("^系统给用户建立一个会期$")
	public void 系统给用户建立一个会期() throws Throwable {
		String currentUserName = userSession.get(UserInfo.USERNAME) ;
		assertNotNull("User Session should have username",currentUserName) ;
		assertEquals("User Session should have username",currentUserName,userIntent.get(UserInfo.USERNAME)) ;
	}

}
