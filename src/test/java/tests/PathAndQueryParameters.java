package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PathAndQueryParameters {

    /**
     * given i create request with parameter 2020-01-02
     * when i send my request to http://api.openrates.io/{date}
     * then the response should contain 2020-01-02
     */
    @Test
    public void pathParamTest(){
     Response response=  given().pathParam("date", "2020-01-02").
                         when().get("http://api.openrates.io/{date}");
        response.prettyPeek().then().statusCode(200);
        assertTrue(response.asString().contains("2020-01-02"));
    }

    /**
     * 400 message test
     * given i create request with wrong parameter 2020-30-02
     * when i send my request to http://api.openrates.io/{date}
     * then the status code should be 400
     *
     */
    @Test
    public void pathParamTestNegative(){

        Response response=  given().pathParam("date", "2020-30-02").
                when().get("http://api.openrates.io/{date}");

        response.prettyPeek().then().assertThat().statusCode(400);

    }

    /**
     * given i create request with query parameter base=USD
     * when i send my request to http://api.openrates.io/latest?base
     * then the response should contain "base": "USD"
     */
    @Test
    public void queryParams(){

        Response response = given().queryParams("base", "USD").
                            when().get("http://api.openrates.io/latest");
        response.prettyPeek();
        assertTrue(response.asString().contains("\"base\":\"USD\""));
    }

    /**
     * given i create request with query parameter base=USD and symbol=MYR
     * when i send my request to http://api.openrates.io/latest
     * then the response should contain "base": "USD"
     * and body should contain MYR
     * but should not contain EUR
     */
    @Test
    public void test2queryParams(){
        Response response = given().queryParams("base", "USD")
                .queryParams("symbols", "MYR")
                .when().get("http://api.openrates.io/latest");
        response.prettyPeek();
        String responseStr = response.asString();
        assertTrue(responseStr.contains("USD") && responseStr.contains("MYR"));
        assertFalse(responseStr.contains("EUR"));

    }



}
