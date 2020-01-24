package tests.day2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BodyAndHeaderVerification {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }


    //verify that response contains header Content-Type: application/json
    // verify taht first name is equal to Neena
    @Test
    public void test1() {
        given().pathParam("id", "101").
                when().get("/employees/{id}").prettyPeek().
                then().
                assertThat().statusCode(200).
                // extract the header value content type and verify
                        and().header("Content-Type", equalTo("application/json")).
                // extract the value of the key first_name and verify
                        and().body("first_name", equalTo("Neena"));

    }

    @Test
    public void test2() {
        JsonPath jsonPath = given().pathParam("id", "101").
                when().get("/employees/{id}").jsonPath();
        // Jsonpath --> class used to navigate through json body and extract values

//        System.out.println(jsonPath.prettyPrint());

        String first_name = jsonPath.getString("first_name");
        System.out.println(first_name);

        String salary = jsonPath.getString("salary");
        System.out.println(salary);

        System.out.println(jsonPath.getString("links.href"));
    }

    //  Using the ORDS api verify that Nancy  is the manager of the finance department.
    // get info of the finance dep
    // get the manager_id
    // get the person with this id

    @Test
    public void managerTest() {
        // get info of the finance dep
        JsonPath jsonPath = given().pathParam("id", "100").
                when().get("/departments/{id}").jsonPath();

        String manager_id = jsonPath.getString("manager_id");
        System.out.println("manager_id = " + manager_id);

        jsonPath = given().pathParam("id", manager_id).
                when().get("/employees/{id}").jsonPath();

        assertThat(jsonPath.getString("first_name"), is("Nancy"));

    }


    @Test
    public void moreJsonPath() {
        JsonPath jsonPath = when().get("/countries/").jsonPath();
        // get all countries in single string
        String allCountries = jsonPath.getString("items.country_name");
        System.out.println(allCountries);


        // get all countries into list of strings
        List<String> countriesList = jsonPath.getList("items.country_name");
        System.out.println(countriesList.size());

        // get the first country in the list
        String countryNumberOne = jsonPath.getString("items.country_name[1]");
        System.out.println(countryNumberOne);

        // get all coutry ids
        List<Integer> ids = jsonPath.getList("items.region_id");
        System.out.println(ids);


    }
}
