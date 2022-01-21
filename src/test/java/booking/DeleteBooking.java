package booking;

import auth.CreateToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import testutilities.TestUtilities;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


public class DeleteBooking {


    CreateToken createToken = new CreateToken();
    String token = createToken.generateToken();
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
     * Test to Delete newly created booking ID
     */
    @Test
    public void testDeleteBookingByID() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/" + res.jsonPath().getJsonObject("bookingid"));
        request.log().all();

        Response response = request.delete();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.statusLine("HTTP/1.1 201 Created");
        validatableResponse.body(Matchers.equalTo("Created"));

    }


    /**
     * Test to delete booking when NO booking id is provided
     */
    @Test
    public void testDeleteBookingByNoIdProvided() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/");
        request.log().all();

        Response response = request.delete();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.statusLine("HTTP/1.1 404 Not Found");
        validatableResponse.body(Matchers.equalTo("Not Found"));

    }

    /**
     * Test to delete booking when Incorrect booking id is provided
     */
    @Test
    public void testDeleteBookingByWrongIdProvided() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/1111");
        request.log().all();

        Response response = request.delete();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
        validatableResponse.statusLine("HTTP/1.1 405 Method Not Allowed");
        validatableResponse.body(Matchers.equalTo("Method Not Allowed"));

    }


}
