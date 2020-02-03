package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthentication {

    @Test
    public void preemptive(){
        given().
                auth().preemptive().basic("admin", "admin").
        when().
                get("https://the-internet.herokuapp.com/basic_auth").
        then().
                statusCode(200);
    }

}
