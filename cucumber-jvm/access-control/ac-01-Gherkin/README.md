ac-01-Gherkin
=============

This is the first of the access-control cucumber-jvm example series.
This Eclipse project provides a skeleton folder structure
* req - this is where the requirements are documented using .feature files
* src - this is where the source code goes.
* test - this is where all the test code goes.

# req - Requirements folder
The requirements folder contains simple .features file in English and Chinese.
* user-admin.feature
* user-admin-zh.feature
These two feature files are semantically equivalent.

user-admin.feature
````
Feature: Account Management
	In order to add new users easily
	As an administrator
	I want to add single or multiple users in one single step 

Scenario: Add a single user
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
［为了］方便添加用户
［生为］管理员
［我要］方便的批量加用户

场景:添加用户
    假如 管理员新增用户 "abc" 邮箱 "abc@abc.com"
    当   用户 "abc" 登入
    那么 系统给用户建立一个会期
````


# src - Source folder
True to Behavior Driven Development, we start off without any code.

# test - Test folder
This is where the test code goes. 
* tests - this folder contains test code to execute the requirements via cucumber-jvm
  * UserAdminTests.java - executes the user-admin.feature file
  * UserAdminTestsZh.java - executes the user-admin-zh.feature file
* suites - this folder contains suite codes to aggregate the test code to execute.
  * AllTest.java - runs both UserAdminTests.java and UserAdminTestsZh.java


