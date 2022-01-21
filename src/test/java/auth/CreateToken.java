package auth;

import booking.GetBookingIds;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import testutilities.TestUtilities;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class CreateToken {

    private static final Logger LOGGER = getLogger(CreateToken.class);


    public String generateToken(){

        Map<String, String> user = new HashMap<>();
        user.put("username", TestUtilities.getProperty("username"));
        user.put("password", TestUtilities.getProperty("password"));

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/auth/");
        request.body(user).log().all();

        Response response = request.post();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        String token = response.path("token");
        LOGGER.info("Generated token is : " + token);
        return token;
    }
}
