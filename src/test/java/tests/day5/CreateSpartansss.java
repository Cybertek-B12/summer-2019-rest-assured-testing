package tests.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CreateSpartansss {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:8000/";
    }

    /**
     * create a new spartan  object
     * by passing valid gender, name, phone from file
     * verify response body
     *  success = A Spartan is Born!
     */
    @Test
    public void postANewSpartannn(){
        /// create the File that we want to send
        File jsonFile = new File("src/test/resources/spartan.json");

        given().log().all()
                .contentType(ContentType.JSON).
                // include the file in the response
                body(jsonFile).
            when().post("/api/spartans").
                prettyPeek().
            then().statusCode(201).
                    body("success", equalTo("A Spartan is Born!"));
    }

    /**
     * create a new spartan  object
     * by passing valid gender, name, phone from file
     * verify response body
     *  success = A Spartan is Born!
     */
    @Test
    public void postANewSpartannnn(){
        Map<String, String> spartan = new HashMap<>();
        spartan.put("gender", "Female");
        spartan.put("name", "Rosa");
        spartan.put("phone", "202323232232");

        given().log().all().
                contentType(ContentType.JSON).
                body(spartan).
            when().post("/api/spartans").
                    prettyPeek().
            then().statusCode(201);

    }

}
