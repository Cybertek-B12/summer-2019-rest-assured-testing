package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonpathWithMapsTests {

    /*
    get the employee with id 100
    verify last_name is King
    verify salary is 24000
     */

    @Test
    public void employeeInformationTest() {
        JsonPath jsonPath = given().pathParam("id", 100).
                contentType(ContentType.JSON).
                when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/{id}")
                .jsonPath();

//        jsonPath.prettyPrint();
// "" in jsonpath it means the root element
        Map<String, Object> personInfo = jsonPath.getMap("");
        System.out.println(personInfo);

        System.out.println(personInfo.get("employee_id"));
        System.out.println(personInfo.get("salary"));
        System.out.println(personInfo.get("hire_date"));

//        System.out.println(personInfo.get("links"));

        Map<String, String> map = jsonPath.getMap("links[3]");
        System.out.println(map);


    }


    @Test
    public void metaWeatherTest() {
        JsonPath jsonPath = given().queryParam("query", "london").
                when().get("https://www.metaweather.com/api/location/search").jsonPath();
        jsonPath.prettyPrint();
        Map<String, Object> map = jsonPath.getMap("[0]");

        System.out.println(map);

        Map<String, Object> expected = new HashMap<>();
        expected.put("woeid", 44418);
        expected.put("location_type", "City");
        expected.put("title", "London");
        expected.put("latt_long", "51.506321,-0.12714");

        assertThat(map, equalTo(expected));
    }


    /*
    call the metaweather api with query param = san
    verify all titles contain string 'san'
     */
    @Test
    public void testNameContains(){
        JsonPath jsonPath = given().queryParam("query", "san").
                when().get("https://www.metaweather.com/api/location/search").jsonPath();
        jsonPath.prettyPrint();

        List<Map<String, String>> list = jsonPath.getList("");
        System.out.println(list.size());
        System.out.println(list);

        for (Map<String, String> city : list) {
            assertTrue(city.get("title").toLowerCase().contains("san"), city.get("title") + " did not contain title");

        }


    }


}
