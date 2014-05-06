/** 
 *  Copyright (c) 2014  Ng Pan Wei
 *  
 *  Permission is hereby granted, free of charge, to any person 
 *  obtaining a copy of this software and associated documentation files 
 *  (the "Software"), to deal in the Software without restriction, 
 *  including without limitation the rights to use, copy, modify, merge, 
 *  publish, distribute, sublicense, and/or sell copies of the Software, 
 *  and to permit persons to whom the Software is furnished to do so, 
 *  subject to the following conditions: 
 *  
 *  The above copyright notice and this permission notice shall be 
 *  included in all copies or substantial portions of the Software. 
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
 *  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
 *  ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 *  SOFTWARE. 
 */
package ngpanwei.stepdefs.case2;

import java.util.Date;
import java.util.List;

import ngpanwei.framework.intention.Intention;
import cucumber.api.Format;
import cucumber.api.Transform;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReservationStepDefs2 {
	Intention intention;

	@Before
	public void setup() {
		intention = new Intention();
	}

	@Given("^that the hotel has the following rooms:$")
	public void that_the_hotel_has_the_following_rooms(List<RoomDef> roomList)
			throws Throwable {
		int count = 0 ;
		System.err.println("Rooms");
		for (RoomDef room : roomList) {
			System.err.println(++count + " - " + room.type + " " + room.quantity);
		}
	}
	@Given("^that the hotel has the following reservations:$")
	public void that_the_hotel_has_the_following_reservations(
			List<ReservationDef> reservationList) throws Throwable {
		int count = 0 ;
		System.err.println("Reservations");
		for (ReservationDef reservation : reservationList) {
			System.err.println(++count + " - " 
						+ Converter.dateString(reservation.checkIn) + " " 
					    + Converter.dateString(reservation.checkOut) + " "
					    + reservation.type);
		}
	}
	@When("^a guest wants to find an available ([^ ]*) room$")
	public void a_guest_wants_to_find_available_room(String type) throws Throwable {
		System.err.println("required room type : "+type) ;
	}
	@When("^the reservation is from (\\d+-\\d+-\\d+) to (\\d+-\\d+-\\d+)$")
	public void the_reservation_is_from_to(@Format("dd-MM-yyyy") Date checkIn, 
			@Transform(Converter.DateConverter.class) Date checkOut) throws Throwable {
		System.err.println("Check In  : "+Converter.dateString(checkIn)) ;
		System.err.println("Check Out : "+Converter.dateString(checkOut)) ;
	}
	@When("^the special requirements are$")
	public void the_special_requirements_are(String specialRequirements) throws Throwable {
		System.err.println("Special Requirements :\n"+specialRequirements) ;
	}	
	@Then("^the reservation is (successful|unsuccessful)$")
	public void the_reservation_is(String expectedResult) throws Throwable {
		System.err.println("Expected result : "+expectedResult);
	}
}
