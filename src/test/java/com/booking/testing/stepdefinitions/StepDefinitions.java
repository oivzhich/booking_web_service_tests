package com.booking.testing.stepdefinitions;

import com.booking.testing.booking.Availability;
import com.booking.testing.booking.AvailabilityActions;
import com.booking.testing.booking.BookRoomActions;
import com.booking.testing.booking.RoomData;
import com.booking.testing.booking.RoomResponse;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
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
}
