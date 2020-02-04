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


    @Test
    public void useKeyStore(){
        // in the trustStore() we pass the location of trust store file and the password of the file
        given().
                trustStore("/path/to/file", "password").
        when().
                get("").
        then().
                statusCode(200);



        // in the keyStore() we pass the location of trust store file and the password of the file
        given().
                keyStore("/path/to/file", "password").
        when().
                get("").
        then().
                statusCode(200);
    }
}
