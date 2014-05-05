#
#  Copyright (c) 2014  Ng Pan Wei
#  
#  Permission is hereby granted, free of charge, to any person 
#  obtaining a copy of this software and associated documentation files 
#  (the "Software"), to deal in the Software without restriction, 
#  including without limitation the rights to use, copy, modify, merge, 
#  publish, distribute, sublicense, and/or sell copies of the Software, 
#  and to permit persons to whom the Software is furnished to do so, 
#  subject to the following conditions: 
#  
#  The above copyright notice and this permission notice shall be 
#  included in all copies or substantial portions of the Software. 
#  
#  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
#  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
#  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
#  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
#  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
#  ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
#  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
#  SOFTWARE. 
# 
# encoding:utf-8

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
	