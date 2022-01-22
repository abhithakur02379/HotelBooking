package booking;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import testutilities.TestUtilities;

import java.io.File;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


public class CreateBooking {

    private static final Logger LOGGER = getLogger(CreateBooking.class);


    File payload = new File(System.getProperty("user.dir") + "/src/test/resources/createbooking.json");

    public Response createNewBooking() throws IOException {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body(payload);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/booking");
        request.log().all();

        Response response = request.post();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        LOGGER.info("Newly created booking id is : " + response.path("bookingid"));
        return response;
    }


}


