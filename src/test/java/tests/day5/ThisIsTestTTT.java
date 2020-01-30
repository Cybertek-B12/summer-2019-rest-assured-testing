package tests.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ThisIsTestTTT {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:8000/";
    }


    /**
     * get all spartans
     * verify status code 200
     */
    @Test
    public void getAllSpartanssss() {
        get("/api/spartans").prettyPeek().then().statusCode(200);
    }

    /**
     * get spartan with id 132
     * verify that response header
     * content type - application.json
     * <p>
     * verify that response body
     * "id": 16,
     * "name": "Sinclair",
     * "gender": "Male",
     * "phone": 9714460354
     */

    @Test
    public void getSingleSpartannn() {
        given().pathParam("id", 132).
                when().get("/api/spartans/{id}").
                prettyPeek().
                then().statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(132)).
                body("name", equalTo("Sinclair")).
                body("gender", equalTo("Male")).
                body("phone", equalTo(9714460354L));
    }

    /**
     * make a get request to search api
     * query param nameContains : ha
     * verify
     * response status code 200
     * header application/json
     * body:   names of all object returns contain the string
     */

    @Test
    public void searchForHaaa() {
        given().log().uri().
                queryParam("nameContains", "ha").
            when().get("/api/spartans/search").
                prettyPeek().
            then().statusCode(200).
                contentType(ContentType.JSON).
                body("content.name", everyItem(containsStringIgnoringCase("ha")));
    }

}
