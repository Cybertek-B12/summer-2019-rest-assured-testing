package tests.o1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanApiTest300 {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:8000/";
    }

    /*
    call the hello endpoint" /api/hello
    verify:
        200 status code
        content type: text/plain;charset=UTF-8
        body: Hello from Sparta
     */
    @Test
    public void hello() {
                when().
                    get("/api/hello").
                then().
                    assertThat().statusCode(200).
                and().
//                   assertThat().contentType("text/plain;charset=UTF-8");
//                   assertThat().contentType(ContentType.TEXT)
                    assertThat().header("Content-Type", is("text/plain;charset=UTF-8")).
                and().
                    assertThat().body(is("Hello from Sparta"));
    }
}