ac-01-Gherkin
=============

This is the first of the access-control cucumber-jvm example series.
This Eclipse project provides a skeleton folder structure
* req - this is where the requirements are documented using .feature files
* src - this is where the source code goes.
* test - this is where all the test code goes.

### req - Requirements folder
The requirements folder contains simple .features file in English and Chinese.
* user-admin.feature
* user-admin-zh.feature
These two feature files are semantically equivalent.

user-admin.feature
````
Feature: Create Account
	In order to protect the system from unauthorized access
	As an administrator
	I want to create accounts for authorized users

Scenario: Create account for a user
	Given the administrator creates account with name "abc" and email "abc@abc.com"
	When the user "abc" logs on to the system
	Then the system creates a session for the user
````
user-admin-zh.feature
````
# language:zh-CN
# encoding:utf-8

@Chinese
功能:用户管理
［为了］保护系统免受未经授权的访问
［作为］管理员
［我要］创建帐户给授权的用户

场景: 给用户创建帐户
    假如 管理员新增用户 "abc" 邮箱 "abc@abc.com"
    当   用户 "abc" 登入
    那么 系统给用户建立一个会期
````


### src - Source folder
True to Behavior Driven Development, we start off without any code.

### test - Test folder
This is where the test code goes. 
* tests - this folder contains test code to execute the requirements via cucumber-jvm
  * UserAdminTests.java - executes the user-admin.feature file through jUnit
  * UserAdminTestsZh.java - executes the user-admin-zh.feature file through jUnit
* suites - this folder contains suite codes to aggregate the test code to execute.
  * AllTest.java - runs both UserAdminTests.java and UserAdminTestsZh.java as a suite through jUnit

UserAdminTests.java
````java
@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true, 
		features = "req/ngpanwei/access/features/admin" ,
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
		tags = { "~@Undefined"  } 
		)
public class UserAdminTestsZh {
}
````

AllTest.java
````java
@RunWith(Suite.class)
@SuiteClasses({ UserAdminTests.class, UserAdminTestsZh.class })
public class AllTest {
}
````
### Notes
To get a richer example on how to express features using Gherkin,
please look at [Part 10 of this tutorial](https://github.com/ngpanwei/examples/tree/master/cucumber-jvm/access-control/ac-10-Explore) 
or [step-definitions](https://github.com/ngpanwei/examples/tree/master/cucumber-jvm/gherkin/step-definitions)  


