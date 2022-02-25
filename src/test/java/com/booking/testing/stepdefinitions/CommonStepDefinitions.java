package com.booking.testing.stepdefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class CommonStepDefinitions {

    @Then("the API should return status {int}")
    public void theAPIShouldReturnStatus(int expectedStatus) {
        restAssuredThat(response -> response.statusCode(expectedStatus));
    }

    @Then("the response content is json")
    public void theResponseContentIsJson() {
        String contentType = lastResponse().getContentType();
        Assert.assertEquals("application/json", contentType);
    }
}
