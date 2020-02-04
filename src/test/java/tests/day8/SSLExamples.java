package tests.day8;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SSLExamples {

    @Test
    public void badSSL(){
        given().
                relaxedHTTPSValidation().
        when().
                get("https://untrusted-root.badssl.com/").
                prettyPeek().
        then().
                statusCode(200);
    }
}
