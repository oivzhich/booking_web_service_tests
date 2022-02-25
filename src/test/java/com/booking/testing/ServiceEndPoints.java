package com.booking.testing;

public enum ServiceEndPoints {
    AVAILABILITY("checkAvailability"), BOOK_ROOM("bookRoom");

    private final String url;

    ServiceEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
