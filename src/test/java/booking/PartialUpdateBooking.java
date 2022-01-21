package booking;

import auth.CreateToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import testutilities.TestUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PartialUpdateBooking {

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
     *  Test to update booking with partial payload
     */
    @Test
    public void testPartialUpdateBooking() {

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "abhishek");
        user.put("lastname", "singh");

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking/" + res.jsonPath().getJsonObject("bookingid"));
        request.body(user).log().all();

        Response response = request.patch();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");
        validatableResponse.body("firstname", Matchers.equalTo("abhishek"));
        validatableResponse.body("lastname", Matchers.equalTo("singh"));
        validatableResponse.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponsePartialUpdateBookingJsonSchema.json"));

    }


    /**
     *  Test to update partial booking with Invalid/Expired token
     */
    @Test
    public void testPartialUpdateBookingWithInvalidToken() {

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "abhishek");
        user.put("lastname", "singh");

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", "b966aae02653bfe");
        request.baseUri(TestUtilities.getProperty("baseURI") + "/bookings/" + res.jsonPath().getJsonObject("bookingid"));
        request.body(user).log().all();

        Response response = request.patch();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.statusLine("HTTP/1.1 404 Not Found");
        validatableResponse.body(Matchers.equalTo("Not Found"));

    }

    /**
     *  Test to update booking with partial payload
     */
    @Test
    public void testPartialUpdateBookingWithInvalidURL() {

        Map<String, String> user = new HashMap<>();
        user.put("firstname", "abhishek");
        user.put("lastname", "singh");

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.cookie("token", token);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/bookings/" + res.jsonPath().getJsonObject("bookingid"));
        request.body(user).log().all();

        Response response = request.patch();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.statusLine("HTTP/1.1 404 Not Found");
        validatableResponse.body(Matchers.equalTo("Not Found"));

    }

}

