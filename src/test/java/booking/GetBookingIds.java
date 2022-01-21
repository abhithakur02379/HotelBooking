package booking;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import testutilities.TestUtilities;

import java.io.IOException;
import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;


public class GetBookingIds {

    private static final Logger LOGGER = getLogger(GetBookingIds.class);


    CreateBooking createBooking = new CreateBooking();
    Response res;

    {
        try {
            res = createBooking.createNewBooking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Test to get all booking Id's
     */
    @Test
    public void testGetAllBookingID() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking");
        request.log().all();

        Response response = request.get();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");
        ArrayList<String> bookingId = response.jsonPath().getJsonObject("bookingid");
        Assert.assertTrue(bookingId.size() > 0);
        LOGGER.info(String.valueOf(bookingId));

    }


    /**
     *  Test to get booking id's with Invalid URL
     */
    @Test
    public void testGetAllBookingIDWithInvalidURL() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/bookings");
        request.log().all();

        Response response = request.get();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.statusLine("HTTP/1.1 404 Not Found");
        validatableResponse.body(Matchers.equalTo("Not Found"));

    }


    /**
     *  Test to get booking Id by First Name and Last Name
     */
    @Test
    public void testGetBookingIDByFirstAndLastName() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.queryParam("firstname", (Object) res.jsonPath().getJsonObject("booking.firstname"));
        request.queryParam("lastname", (Object) res.jsonPath().getJsonObject("booking.lastname"));
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/");
        request.log().all();

        Response response = request.get();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");
        ArrayList<String> bookingId = response.jsonPath().getJsonObject("bookingid");
        if (bookingId.size() > 0){
            LOGGER.info(String.valueOf(bookingId));
        } else {
            LOGGER.info("No Booking Id Found with Provided Values");
        }
        Assert.assertTrue(bookingId.size() > 0);

    }


    /**
     *  Test to get booking Id by Check-in date and Check-Out date
     */
    @Test
    public void testGetBookingIDByCheckInAndCheckOutDate() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.queryParam("checkin",(Object) res.jsonPath().getJsonObject("booking.bookingdates.checkin"));
        request.queryParam("checkout",(Object) res.jsonPath().getJsonObject("booking.bookingdates.checkout"));
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/");
        request.log().all();

        Response response = request.get();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");
        ArrayList<String> bookingId = response.jsonPath().getJsonObject("bookingid");
        if (bookingId.size() > 0){
            LOGGER.info(String.valueOf(bookingId));
        } else {
            LOGGER.info("No Booking Id Found with Provided Values");
        }
        Assert.assertTrue(bookingId.size() > 0);

    }


}
