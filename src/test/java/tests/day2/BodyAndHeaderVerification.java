package tests.day2;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BodyAndHeaderVerification {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }


    //verify that response contains header Content-Type: application/json
    // verify taht first name is equal to Neena
    @Test
    public void test1() {
        given().pathParam("id", "101").
                when().get("/employees/{id}").prettyPeek().
                then().
                assertThat().statusCode(200).
                // extract the header value content type and verify
                and().header("Content-Type", equalTo("application/json")).
                // extract the value of the key first_name and verify
                and().body("first_name", equalTo("Neena"));





    }
}
