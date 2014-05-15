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
package ngpanwei.tests.unit.login;

import static org.junit.Assert.assertNotNull;
import ngpanwei.jEssence.app.login.LoginServlet;
import ngpanwei.mock.servlet.ServletUnitTest;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginUnitSteps extends ServletUnitTest {
	protected LoginServlet servlet ;
	
	@Before
	public void beforeScenario() {
		try {
			this.reset() ;
			servlet = new LoginServlet();
			servlet.init(config) ;
		} catch (Exception ignore) {
		}
	}	

	@Given("^user is on any page$")
	public void user_is_on_any_page() throws Throwable {
	}
	@When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_and(String username, String password) throws Throwable {
		requestH.setParameter("username", username);
		requestH.setParameter("password", password);
	}

	@When("^submit login$")
	public void submit_login() throws Throwable {
		servlet.doGet(request, response);
	}

	@Then("^the user has logged in successfully$")
	public void the_user_has_logged_in_successfully() throws Throwable {
		String theSessionUserName = (String)session.getAttribute("user") ;
		System.err.println("Session user: "+theSessionUserName);
		assertNotNull("UserName should be assigned to session",theSessionUserName) ;
	}

}


