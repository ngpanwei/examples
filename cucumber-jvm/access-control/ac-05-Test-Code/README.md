ac-05-Test-Code
===============

This is the fifth part of the access-control cucumber-jvm example series.

# test.ngpanwei.framework - framework code for implementing tests
* Intent.java - this class tracks what the user intends to do.

# test.ngpanwei.stepdefs - Test Step Definition folder
The step definitions are updated with code to invoke the source codes
* UserAdminStepDefs.java - step definitions now invoke the source API.
* UserAdminStepDefsZh.java - step definitions now invoke the source API.

UserAdminStepDefs.java
````java
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
````