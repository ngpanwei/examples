cucumber-jvm Step-Definitions
=============================

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

The first step definition is implemented as:
````java
	@Given("^that the hotel has the following rooms:$")
	public void that_the_hotel_has_the_following_rooms(List<RoomDef> roomList)
			throws Throwable {
		// ...
	}
```

The second step definition is implemented as:
````java
	@Given("^that the hotel has the following reservations:$")
	public void that_the_hotel_has_the_following_reservations(
			List<ReservationDef> reservationList) throws Throwable {
		// ...
	}
````
Note that the ReservationDef class has a custom converter.
````java
public class ReservationDef {
	@XStreamConverter(Converter.DateConverter.class)
	Date checkIn;
	@XStreamConverter(Converter.DateConverter.class)
	Date checkOut;
	String type;
}
````
The custom formatter is implemented as:
````java
public class Converter {
	public static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy") ;
	
	public static String dateString(Date date) {
		return format.format(date) ;
	}
	public static class DateConverter extends Transformer<Date> {
		@Override
		public Date transform(String value) {
			Date date = null ;
			try {
				date = format.parse(value) ;
			} catch (ParseException ignore) {
			}		
			return date;
		}
	}
}
````
The next step definition uses an argument not encapsulated with quotes
````java
	@When("^a guest wants to find an available ([^ ]*) room$")
	public void a_guest_wants_to_find_available_room(String type) throws Throwable {
		// ...
	}
````
You can use two ways to extract dates
````java
	@When("^the reservation is from (\\d+-\\d+-\\d+) to (\\d+-\\d+-\\d+)$")
	public void the_reservation_is_from_to(@Format("dd-MM-yyyy") Date checkIn, 
			@Transform(Converter.DateConverter.class) Date checkOut) throws Throwable {
		System.err.println("Check In  : "+Converter.dateString(checkIn)) ;
		System.err.println("Check Out : "+Converter.dateString(checkOut)) ;
	}
````
Of course, you can have multi-line text like normal text
````java
	@When("^the special requirements are$")
	public void the_special_requirements_are(String specialRequirements) throws Throwable {
		// ...
	}	
````
You can have options declared:
````java
	@Then("^the reservation is (successful|unsuccessful)$")
	public void the_reservation_is(String expectedResult) throws Throwable {
		// ...
	}
````

