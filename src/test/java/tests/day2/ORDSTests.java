package tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ORDSTests {


    @Test
    public void employeeTableTest() {
        Response response = given().pathParam("id", "100").
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/{id}");
        response.then().statusCode(200).and().contentType("application/json");
        String responseStr = response.asString();
        assertThat(responseStr, containsString("100"));
        assertThat(responseStr, containsString("King"));

    }


    @Test
    public void countriesTableTest() {
        Response response = given().pathParam("id", "AR").
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/countries/{id}");
        response.then().statusCode(200).and().contentType("application/json");
        assertThat(response.asString(), containsString("AR"));
        assertThat(response.asString(), containsString("Argentina"));

    }


    @Test
    public void departmentsTableTest(){
        Response response = given().pathParam("id", "2000").
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/departments/{id}");
        response.then().statusCode(404);

        String contentType = response.header("Content-Type");
        assertThat(contentType, is("text/html"));
        Assertions.assertEquals(contentType, "text/html");

    }
}
