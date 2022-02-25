package com.booking.testing.booking;

import net.serenitybdd.rest.SerenityRest;

public class RoomResponse {
    public RoomData returnedRoom() {
        return SerenityRest.lastResponse().getBody().as(RoomData.class);
    }
}
