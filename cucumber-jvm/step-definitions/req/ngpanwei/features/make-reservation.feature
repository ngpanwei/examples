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

Feature: Make Reservation
	In order to get confirmed accommodation with a hotel
	As a hotel guest 
	I want to make a reservation

Scenario: Make Reservation
	Given that the hotel has the following rooms:
		| type   | quantity |	
		| single | 3        |	
		| dual   | 2        |	
	Given that the hotel has the following reservations:
	    | checkIn  | checkOut  | type   |
		| 1-6-1998 | 10-6-1998 | single |
		| 1-6-1998 | 5-6-1998  | single |
		| 4-6-1998 | 7-6-1998  | single |
	When a guest wants to find an available single room 
	And the reservation is from 2-6-1998 to 3-6-1998
	And the special requirements are 
	"""
	  I want a room facing the sea.
	  I require an early check-in.
	 """
	Then the reservation is successful
	