package ping;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HealthCheck {


    @Test
    public void checkRequest() {

        Response response =

                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get("/ping")
                .then()
                        .statusCode(201)
                        .log().body()
                        .extract().response();

        Assert.assertEquals("Created", response.asString());
    }


}


