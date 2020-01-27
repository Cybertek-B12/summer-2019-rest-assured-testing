package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonPathTests {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }

    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}"
     * when user makes get request with path param id=1
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void validateRegionNameTest() {
        given().pathParam("id", 1)
                .when().get("/regions/{id}")
                .prettyPeek()
                .then().assertThat().statusCode(200)
                .and().assertThat().body("region_id", equalTo(1))
                .and().assertThat().body("region_name", equalTo("Europe"));
    }


    @Test
    public void validateRegionNameTest1() {
        Response response = given().pathParam("id", 1)
                .when().get("/regions/{id}");
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.getString("region_id");
        assertThat(id, equalTo("1"));
        String name = jsonPath.getString("region_name");
        assertThat(name, equalTo("Europe"));

    }

    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/{id}"
     * accept type is json
     * when user makes get request with path param id=100
     * and last_name id is equals to King
     * then assert that status code is 200
     */
    @Test
    public void testLastName() {

        given().contentType(ContentType.JSON).                       // accept type is json
                pathParam("id", 100).    // request with path param id=100
                when().get("/employees/{id}").                         // when user makes get request
                then().assertThat().statusCode(200).                            // assert that status code is 200
                and().assertThat().body("last_name", is("King"));  // last_name id is equals to King
    }

    /**
     * given url "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/{id}"
     * accept type is json
     * when user makes get request with path param id=100
     * and first href is equal to "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/100"
     * then assert that status code is 200
     */

    @Test
    public void link1() {
        Response response = given().contentType(ContentType.JSON).                       // accept type is json
                pathParam("id", 100).    // request with path param id=100
                when().get("/employees/{id}");// when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        // links.href[0] --> in the json file, find key links, then find its children href and get the first one
        String link = jsonPath.getString("links.href[0]");
        assertThat(link, equalTo("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/100"));

    }
    /*
    Given accept type is JSON
    When users sends a GET request to "/employees"
    Then status code is 200
    And Content type is application/json
    And last_name of the first employee from payload is "King"
    And salary of the first employee from payload is "24000"
    And last_name of the last employee from payload is "Mourgos"
    And salary of the last employee from payload is "5800"
         */

    @Test
    public void testFirstAndLast() {
        Response response = given().contentType(ContentType.JSON).                       // accept type is json
                when().get("/employees");// when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String firstLN = jsonPath.getString("items.last_name[0]");
        assertThat(firstLN, is("King"));
        String firstS = jsonPath.getString("items.salary[0]");
        assertThat(firstS, is("24000"));

        // -1 means last one, so items.last_name[-1] gets the last lastname
        String lastFn = jsonPath.getString("items.last_name[-1]");
        assertThat(lastFn, is("Mourgos"));

        String lastS = jsonPath.getString("items.salary[-1]");
        assertThat(lastS, is("5800"));
        System.out.println("_______________________");

    }

    /*
    Given accept type is JSON
    When users sends a GET request to "/employees"
    Then status code is 200
    And Content type is application/json
    And verify first_name of the employee with employee_id 102  is equal Lex
     */

    @Test
    public void getValuebasedOnAnotherValue() {
        Response response = given().contentType(ContentType.JSON).                       // accept type is json
                when().get("/employees");// when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String string = jsonPath.getString("items.find {it.employee_id==102}.first_name");
        assertThat(string, is("Lex"));

    }

    /*
    Get all the countries using the "/countries"
    verify following countries are listed: Argentina, Brazil, Mexico, United States of America, Zambia
     */

    @Test
    public void testCountryList(){
        Response response = given().contentType(ContentType.JSON).                       // accept type is json
                when().get("/countries");// when user makes get request
        List<String> actualList = response.jsonPath().getList("items.country_name");
        System.out.println(actualList);
        List<String> expected = Arrays.asList("Argentina" ,"Brazil", "Mexico", "United States of America", "Zambia");
        System.out.println(expected);


        assertThat(actualList, contains("Argentina" ,"Brazil", "Mexico", "United States of America", "Zambia"));

        for (String expectedCountry : expected) {
            assertTrue(actualList.contains(expectedCountry));
        }
    }

     /*

    Given accept type is JSON
    When users sends a GET request to "/employees"
    Then status code is 200
    And Content type is application/json
    And verify all salaries are bigger 100
     */

     @Test
    public void salaryListTest(){
         Response response = when().get("/employees");
         response.then().statusCode(200);

         List<Integer> list = response.jsonPath().getList("items.salary");
         System.out.println(list);

         // make sure that every item in the list (each salary) is bigger than 100
         assertThat(list, everyItem(greaterThan(100)));
     }

}
