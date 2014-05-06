Step-Definitions
================

This project provides examples on how to create different 
kinds of step definitions using cucumber-jvm.
It expands on the examples in [cucumber step-definitions](http://cukes.info/step-definitions.html)

make-reservation.feature
```
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
````


