package tests.day7;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BookITtests {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("bookitQa1Url");
    }

    /**
     * verify no sigh message
     */
    @Test
    public void test(){
        when().get("/api/campuses")
                .prettyPeek().
        then().statusCode(422);
    }
}
