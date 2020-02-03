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

    /**
     * get access token
     *
     */

    @Test
    public void testAccessToken(){
        // team_member_email
        String email = ConfigurationReader.get("team_member_email");
        String password = ConfigurationReader.get("team_member_password");

        given().log().everything().
                queryParam("email", email).
                queryParam("password", password).
            when().get("/sign").
                prettyPeek().
            then().statusCode(200);

    }

}
