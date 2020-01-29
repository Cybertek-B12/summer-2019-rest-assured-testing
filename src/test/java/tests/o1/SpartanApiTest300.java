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
     /*
     Bad headers test
    call the hello endpoint" /api/hello
    header-->
        accept : application/json
    verify:
        406 status code
     */

     @Test
    public void badHeaderTest(){
//         given().accept(ContentType.JSON).when().get("/api/hello").then().statusCode(406);
         given().accept("application/json").when().get("/api/hello").then().statusCode(406);

     }

 /*
    call the hello endpoint" /api/spartans
        header-->
        accept : application/json
    verify:
        200 status code
        content type: application/json;charset=UTF-8
     */

    @Test
     public void contentTypeJsonTest(){
        given().accept(ContentType.JSON).
            when().get("/api/spartans").
            then().statusCode(200).
                contentType("application/json;charset=UTF-8");
     }

  /*
    call the hello endpoint" /api/spartans
        header-->
        accept : application/xml
    verify:
        200 status code
        content type: application/xml;charset=UTF-8

     */

    @Test
    public void contentTypeXmlTest(){
        given().accept(ContentType.XML).
            when().get("/api/spartans").
            then().statusCode(200).
                contentType("application/xml;charset=UTF-8");

    }
}