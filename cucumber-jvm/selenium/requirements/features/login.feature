Feature: Login to the website
As a user I want to login to the website from any visitor.

@Sample 
Scenario: login successful
	Given user is on any page
	When user enters "username" and "password"
	And submit login
	Then the user has logged in successfully

Scenario: login failure
	Given user is on any page
	When user enters "username" and "wrong password"
	And submit login
	Then the user cannot login

Scenario Outline: login data
	Given user is on any page
	When user enters "<username>" and "<password>"
	And submit login
	Then the login is "<result>"

Examples: 
	| username | password | result |
	| username | password | pass   |
	| username | wrong    | fail   |

