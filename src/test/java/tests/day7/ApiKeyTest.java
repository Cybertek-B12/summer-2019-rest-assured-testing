package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiKeyTest {
    /**
     * test the 401 status code by not passing the api key
     */
    @Test
    public void noKey(){
        given().queryParam("t", "A is star born").
                when().get("http://www.omdbapi.com").
                prettyPeek().
                then().statusCode(401).
                body("Error", equalTo("No API key provided."));
    }

    /**
     * call the omdbapi with a valid api key
     */
    @Test
    public void withKey(){
        given().
                queryParam("apikey", "e0484f01").
                queryParam("t", "Goonies").
            when().get("http://www.omdbapi.com").
                prettyPeek().
            then().statusCode(200).
                body("Title", equalTo("The Goonies")).
                body("Year", equalTo("1985"));
    }
}
