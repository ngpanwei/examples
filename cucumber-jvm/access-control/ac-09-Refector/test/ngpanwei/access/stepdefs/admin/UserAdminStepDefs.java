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
package ngpanwei.access.stepdefs.admin;

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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * User admin feature step definitions
 * @author ngpanwei
 */
public class UserAdminStepDefs {
	Intention userIntent ;
	Session userSession ;
	IUserDataAccess userDataAccess ;
	IUserAdminHandler adminHandler ;
	IAuthenticationHandler authenticationHandler ;
	ISessionHandler sessionHandler ;
	/**
	 * Initialization before the execution of each scenario.
	 */
	@Before 
	public void setup() {
		TestExecutionContext.init() ;
		userDataAccess = (IUserDataAccess)ObjectFactory.getInstance(IUserDataAccess.class) ;
		userDataAccess.reset() ;
		adminHandler = (IUserAdminHandler) ObjectFactory.getInstance(IUserAdminHandler.class) ;
		authenticationHandler = (IAuthenticationHandler)ObjectFactory.getInstance(IAuthenticationHandler.class) ;
		sessionHandler = (ISessionHandler)ObjectFactory.getInstance(ISessionHandler.class) ;
		userIntent = new Intention() ;
		userSession = sessionHandler.getSession()  ;
	}
	@Given("^the administrator creates account with name \"(.*?)\" and email \"(.*?)\"$")
	public void the_administrator_creates_account_with_name_and_email(String username, String email) throws Throwable {
		UserInfo userInfo = new UserInfo() ;
		userInfo.put(UserInfo.USERNAME, username) ;
		userInfo.put(UserInfo.USEREMAIL, email) ;
		UserAccount userAccount = adminHandler.createUser(userInfo) ;
		assertNotNull("User Account should not be null",userAccount) ;
	}
	
	@Given("^the user \"(.*?)\" sets the password to \"(.*?)\"$")
	public void the_user_sets_the_password_to(String username, String password) throws Throwable {
		UserInfo userInfo = new UserInfo() ;
		userInfo.put(UserInfo.USERNAME, username) ;
		userInfo.put(UserInfo.PASSWORD, password) ;
		UserAccount userAccount = adminHandler.changePassword(userInfo) ;
		assertNotNull("User Account should not be null",userAccount) ;

		userIntent.put(UserInfo.USERNAME, username) ;
		userIntent.put(UserInfo.PASSWORD, password) ;
	}

	@When("^the user \"(.*?)\" logs on to the system$")
	public void the_user_logs_on_to_the_system(String username) throws Throwable {
		authenticationHandler.login(username, userIntent.get(UserInfo.PASSWORD)) ;
	}

	@Then("^the system grants access to the user$")
	public void the_system_grants_access_to_the_user() throws Throwable {
		String currentUserName = userSession.get(UserInfo.USERNAME) ;
		assertNotNull("User Session should have username",currentUserName) ;
		assertEquals("User Session should have username",currentUserName,userIntent.get(UserInfo.USERNAME)) ;
	}
}
