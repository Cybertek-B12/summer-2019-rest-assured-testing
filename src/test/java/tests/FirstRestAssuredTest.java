package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class FirstRestAssuredTest {

    /**
     * wehn i send requesto to http://api.openrates.io/latest
     * Then the status must 200
     */

    @Test
    public void verifyStatusCode(){
        // response --> response that is sent by server as result of our request
        // get --> send the request to given url

        Response response = when().get("http://api.openrates.io/latest");
        // print the response
        response.prettyPrint();
        // verify the status code
        // verifies that status matches the provided option
        response.then().statusCode(200);

    }

    /**
     * When i send requesto to http://api.openrates.io/latest
     * Then body should contain  "base": "EUR"
     */

    @Test
    public void verifyBodyContains(){
        Response response = when().get("http://api.openrates.io/latest");
        // asString() --> returns the body as a single string
        String bodyStr = response.asString();
        System.out.println(bodyStr);
        assertTrue(bodyStr.contains("\"base\":\"EUR\""));
    }

}
