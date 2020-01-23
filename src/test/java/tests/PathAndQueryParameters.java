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
}
