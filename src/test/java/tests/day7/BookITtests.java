package tests.day7;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.TokenUtility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

        Response response = given().log().everything().
                queryParam("email", email).
                queryParam("password", password).
                when().get("/sign");
        String accessToken = response.path("accessToken");


        response. prettyPeek().
            then().statusCode(200);

       System.out.println("accessToken = " + accessToken);
    }
    // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTc0IiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.raPyuRcS8xM5eOhEW4qxepwbs9XHPjlV4Xo8CIPxaPs

    /**
     * get all campuses by providing access token
     */

    @Test
        public void getAllCampuses(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTc0IiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.raPyuRcS8xM5eOhEW4qxepwbs9XHPjlV4Xo8CIPxaPs";
        given().
                header("Authorization", token).
        when().
                get("/api/campuses").
                prettyPeek().
        then().
                statusCode(200);
    }

    // break 20 mins

    /**
     * Unit Test the token utility
     */
    @Test
    public void test2(){
        String token = TokenUtility.getToken("teacher");
        assertThat(token, not(emptyString()));
    }
}
