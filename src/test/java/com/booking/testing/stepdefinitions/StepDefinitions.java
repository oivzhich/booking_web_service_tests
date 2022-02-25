package com.booking.testing.stepdefinitions;

import com.booking.testing.booking.Availability;
import com.booking.testing.booking.AvailabilityActions;
import com.booking.testing.booking.BookRoomActions;
import com.booking.testing.booking.RoomData;
import com.booking.testing.booking.RoomResponse;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import utils.DateHelper;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    @Steps
    RoomResponse roomResponse;
    @Steps
    AvailabilityActions availabilityActions;
    @Steps
    BookRoomActions bookRoomActions;

    RoomData actualRoomData;
    Integer expectedTotalPrice;

    @DataTableType
    public Availability availabilityEntry(Map<String, String> entry) {
        return new Availability(entry);
    }

    @DataTableType
    public RoomData bookRoomEntry(Map<String, String> entry) {
        return new RoomData(entry);
    }

    @When("the user gets available room for today")
    public void theUserGetsAvailableRoomForToday() {
        availabilityActions.checkAvailability(DateHelper.today());
    }

    @And("the API should return availability with following data:")
    public void theAPIShouldReturnAvailabilityWithFollowingData(Availability expectedAvailability) {
        Availability actualAvailability = SerenityRest.lastResponse().jsonPath().getObject("", Availability.class);
        assertThat(actualAvailability).usingRecursiveComparison().isEqualTo(expectedAvailability);
    }

    @When("the user gets available room for tomorrow")
    public void theUserGetsAvailableRoomForTomorrow() {
        availabilityActions.checkAvailability(DateHelper.daysFromToday(1));
    }

    @When("the user gets available room for {string}")
    public void theUserGetsAvailableRoomFor(String date) {
        availabilityActions.checkAvailability(date);
    }

    @When("I book a room with following data:")
    public void iBookARoomWithFollowingData(RoomData roomData) {
        bookRoomActions.bookRoom(roomData);
    }

    @And("a room with following data should be added:")
    public void aRoomWithFollowingDataShouldBeAdded(RoomData expectedRoomData) {
        RoomData actualRoomData = roomResponse.returnedRoom();
        assertThat(actualRoomData).usingRecursiveComparison().isEqualTo(expectedRoomData);
    }

    @When("I book a room starting {string} for {string}")
    public void iBookARoomStartingFor(String checkInDate, String numOfDays) {
        bookRoomActions.bookRoom(new RoomData(checkInDate, Integer.parseInt(numOfDays)));
    }

    @And("a room booking was successfully added starting {string} for {string}")
    public void aRoomBookingWasSuccessfullyAddedStartingFor(String checkInDate, String numOfDays) {
        RoomData expectedRoomData = new RoomData(checkInDate, Integer.parseInt(numOfDays));
        actualRoomData = roomResponse.returnedRoom();

        expectedRoomData.setCheckOutDate(
                DateHelper.daysFrom(expectedRoomData.getCheckInDate(), expectedRoomData.getNumOfDays()));
        expectedRoomData.setTotalPrice(expectedTotalPrice);

        assertThat(actualRoomData).usingRecursiveComparison().ignoringFields("numOfDays").isEqualTo(expectedRoomData);
    }

    @And("room_available field should be grater than {int}")
    public void fieldShouldBeGraterThan(int expected) {
        Availability actualAvailability = SerenityRest.lastResponse().jsonPath().getObject("", Availability.class);
        assertThat(actualAvailability.getRooms_available()).isGreaterThan(expected);
    }

    @When("I calculate expectedTotalPrice for room booked starting {string} for {string}")
    public void iCalculateExpectedTotalPriceForBroomBookedStartingFor(String checkInDate, String numOfDays) {
        expectedTotalPrice = bookRoomActions.getExpectedTotalPrice(checkInDate, Integer.parseInt(numOfDays));
    }

    @Given("I calculate expectedTotalPrice for room booked starting")
    public void iCalculateExpectedTotalPriceForRoomBookedStarting() {

    }
}
