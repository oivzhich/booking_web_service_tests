package com.booking.testing.booking;

import com.booking.testing.ServiceEndPoints;
import config.PropertiesConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.aeonbits.owner.ConfigFactory;

public class AvailabilityActions {
    protected static final PropertiesConfiguration conf =
            ConfigFactory.create(PropertiesConfiguration.class, System.getProperties());

    @Step("Check availability by date")
    public void checkAvailability(String date) {
        SerenityRest.given()
                    .relaxedHTTPSValidation()
                    .contentType("application/json")
                    .header("Content-Type", "application/json")
                    .log()
                    .all()
                    .when()
                    .get(String.format("%s/%s/%s", conf.baseUrl(), ServiceEndPoints.AVAILABILITY.getUrl(), date));
    }
}
