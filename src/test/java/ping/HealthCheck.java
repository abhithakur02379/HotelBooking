package ping;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import testutilities.TestUtilities;

public class HealthCheck {


    @Test
    public void checkRequest() {

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.baseUri(TestUtilities.getProperty("baseURI") + "/ping");
        request.log().all();

        Response response = request.get();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.statusLine("HTTP/1.1 201 Created");
        validatableResponse.body(Matchers.equalTo("Created"));

    }


}


