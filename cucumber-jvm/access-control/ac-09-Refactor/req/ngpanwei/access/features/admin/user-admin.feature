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

Scenario: Create account for a user
	Given the administrator creates account with name "abc" and email "abc@abc.com"
	Given the user "abc" sets the password to "abc-password"
	When the user "abc" logs on to the system
	Then the system grants access to the user