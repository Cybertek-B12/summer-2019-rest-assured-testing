package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class FirstRestAssuredTest {

    /**
     * when i send request to http://api.openrates.io/latest
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

    /**
     * When i send requesto to http://api.openrates.io/latest
     * Then response should contain header application/json
     */

    @Test
    public void verifyHeader1(){
        Response response = when().get("http://api.openrates.io/latest");
        // response.header() --> returns the value of the provided header
        String contentType = response.header("Content-Type");
        String date = response.header("Date");

        System.out.println("contentType = " + contentType);
        System.out.println("date = " + date);

        assertEquals("application/json", contentType);
        assertTrue(date.contains("2020"));

    }


    /**
     * When i send requesto to http://api.openrates.io/latest
     * Then response should contain header application/json
     */
    @Test
    public void verifyContentType(){
        Response response = when().get("http://api.openrates.io/latest");
        // response.getContentType --> returns the content type of the response
        String contentType = response.getContentType();
        System.out.println(contentType);
        // response.getStatusCode --> returns the status code of teh response
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);

        assertEquals("application/json", contentType);

        // this line will print and also very
        response.prettyPeek().then().statusCode(200);
    }

    /**
     *    when i send request to  http://api.zippopotam.us/us/22031
     *    Then the status must 200
     *    And verify that response contains Fairfax
     */


    @Test
    public void statusCodeVerify(){
        Response response = when().get("http://api.zippopotam.us/us/22031");
        response.prettyPrint();
        response.then().statusCode(200);
        assertTrue(response.asString().contains("Fairfax"));
    }

    /**
     *    when i send request to  http://api.zippopotam.us/us/22031111
     *    Then the status must 404
     */
    @Test
    public void statusCodeVerify2(){
        Response response = when().get("http://api.zippopotam.us/us/220313234");
        response.prettyPeek().then().statusCode(404);
    }

}
