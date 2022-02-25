package com.booking.testing.booking;

import com.booking.testing.ServiceEndPoints;
import config.PropertiesConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.aeonbits.owner.ConfigFactory;
import utils.DateHelper;

public class BookRoomActions {
    protected static final PropertiesConfiguration conf =
            ConfigFactory.create(PropertiesConfiguration.class, System.getProperties());

    @Steps
    AvailabilityActions availabilityActions;

    @Step("Book a room")
    public void bookRoom(RoomData roomData) {
        SerenityRest.given()
                    .relaxedHTTPSValidation()
                    .contentType("application/json")
                    .header("Content-Type", "application/json")
                    .log()
                    .all()
                    .body(roomData)
                    .when()
                    .post(String.format("%s/%s", conf.baseUrl(), ServiceEndPoints.BOOK_ROOM.getUrl()));
    }

    public Integer getExpectedTotalPrice(String checkInDate, Integer numOfDays) {
        int totalCount = 0;

        for (int i = 0; i < numOfDays; i++) {
            Availability availability = availabilityActions.checkAvailability(DateHelper.daysFrom(checkInDate, i));
            totalCount = totalCount + availability.getPrice();
        }
        return totalCount;
    }
}
