package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonpathWithMapsTests {

    /*
    get the employee with id 100
    verify last_name is King
    verify salary is 24000
     */

    @Test
    public void employeeInformationTest(){
        JsonPath jsonPath = given().pathParam("id", 100).
                contentType(ContentType.JSON).
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/{id}")
                .jsonPath();

//        jsonPath.prettyPrint();

        Map<String, Object> personInfo = jsonPath.getMap("");
        System.out.println(personInfo);

        System.out.println(personInfo.get("employee_id"));
        System.out.println(personInfo.get("salary"));
        System.out.println(personInfo.get("hire_date"));

        System.out.println(personInfo.get("links"));

    }

}
