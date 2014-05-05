ac-10-Explore
=============

This is the tenth part of the access-control cucumber-jvm example series.

### req - explore requirements in terms of scope and acceptance
* access-control.xmind - A Mindmap using xmind to explore requirements
* Feature-Exploration.png - an exported png to show exploration of access control features
* 01-Create-Account.png - an exported png to show exploration of Create Account scenarios 


Feature-Exploration.png

![Feature Exploration MindMap](req/ngpanwei/access/Feature-Exploration.png)

01-Create-Account.png

![Feature Exploration MindMap](req/ngpanwei/access/01-Create-Account.png)

user-admin.feature
````
Feature: Create Account
	In order to protect the system from unauthorized access
	As an administrator
	I want to create accounts for authorized users
	- considerations
	  - duplicate accounts
	  - batch creation

Scenario: Create a new user account
	Given the administrator has created account with name "abc" and email "abc@abc.com"
	Given the user "abc" sets the password to "abc-password"
	Then the user "def" "cannot" login with password "xxx"
	Then the user "abc" "cannot" login with password "xxx"
	Then the user "abc" "can" login with password "abc-password"

Scenario: Create a duplicate account fails
	Given the administrator has created account with name "abc" and email "abc@abc.com"
	When the administrator creates account with name "abc" and email "abc@abc.com"
	Then no new account is created

Scenario: Create multiple accounts
	Given the administrator has created account with name "abc" and email "abc@abc.com"
	When the administrator creates accounts names and emails:
		| def | def@def.com |
		| hij | hij@hij.com |
	When the users set the following passwords:
		| abc | abc-password |
		| def | def-password |
		| hij | hij-password |
	Then the user "abc" "cannot" login with password "xxx"
	Then the user "def" "can" login with password "def-password"
````