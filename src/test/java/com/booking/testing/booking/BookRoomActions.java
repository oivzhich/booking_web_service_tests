package com.booking.testing.booking;

import com.booking.testing.ServiceEndPoints;
import config.PropertiesConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.aeonbits.owner.ConfigFactory;

public class BookRoomActions {
    protected static final PropertiesConfiguration conf =
            ConfigFactory.create(PropertiesConfiguration.class, System.getProperties());

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
}
