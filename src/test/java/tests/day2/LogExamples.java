package tests.day2;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class LogExamples {

    @Test
    public void test1(){
        // request logging
        // log().all(). --> prints everything in the request
        given().log().everything().
                pathParam("id", "7").
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}").
                then().
                statusCode(200);

    }
}
