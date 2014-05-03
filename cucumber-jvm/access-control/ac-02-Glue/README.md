ac-02-Glue
==========

This is the second part of the access-control cucumber-jvm example series.

# test - Test folder
The test folder is now updated with empty step definitions
* stepdefs - this folder contains the glue code to implement the tests
** UserAdminStepDefs.java - this is the glue code for user-admin.feature
** UserAdminStepDefsZh.java - this is the glue code for user-admin-zh.feature

* tests - the contents of this folder is updated to glue the step definition codes
** UserAdminTests.java - this is updated to glue the step definition codes.
** UserAdminTestsZh.java - this is updated to glue the step definition codes.

The "glue" option tells cucumber-jvm where to find the code that maps to the Gherkin steps.
UserAdminTests.java
````java
@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true, 
		features = "req/ngpanwei/access/features/admin" ,
		glue = "ngpanwei.access.stepdefs.admin" ,
		tags = { "~@Undefined"  } 
		)
public class UserAdminTests {
}
````

UserAdminTestsZh.java
````java
@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true, 
		features = "req/ngpanwei/access/featuresZh" ,
		glue = "ngpanwei.access.stepdefsZh" ,
		tags = { "~@Undefined"  } 
		)
public class UserAdminTestsZh {
}
````
