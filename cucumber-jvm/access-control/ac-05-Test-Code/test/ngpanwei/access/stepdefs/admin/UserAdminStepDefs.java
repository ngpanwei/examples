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

import static org.junit.Assert.assertNotNull;
import ngpanwei.access.admin.IUserAdminHandler;
import ngpanwei.access.admin.UserAccount;
import ngpanwei.access.admin.UserAdminHandler;
import ngpanwei.access.admin.UserInfo;
import ngpanwei.access.session.ISessionHandler;
import ngpanwei.access.session.Session;
import ngpanwei.access.session.SessionHandler;
import ngpanwei.framework.intention.Intention;
import cucumber.api.PendingException;
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
	IUserAdminHandler adminHandler ;
	ISessionHandler sessionHandler ;
	/**
	 * Initialization before the execution of each scenario.
	 */
	@Before 
	public void setup() {
		userIntent = new Intention() ;
		userSession = null ;
		adminHandler = new UserAdminHandler() ;
		sessionHandler = new SessionHandler() ;
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
		userSession = sessionHandler.login(username, userIntent.get(UserInfo.PASSWORD)) ;
	}

	@Then("^the system grants access to the user$")
	public void the_system_grants_access_to_the_user() throws Throwable {
		assertNotNull("User Session should not be null",userSession) ;
	}
}
