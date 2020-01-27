package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class JsonPathTests {

    @BeforeAll
    public static void setUp(){
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
    public void validateRegionNameTest(){
        given().pathParam("id",1)
                .when().get("/regions/{id}")
                .prettyPeek()
                .then().assertThat().statusCode(200)
                .and().assertThat().body("region_id",equalTo(1))
                .and().assertThat().body("region_name",equalTo("Europe"));
    }


    @Test
    public void validateRegionNameTest1(){
        Response response = given().pathParam("id",1)
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
    public void testLastName(){

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
    public void link1(){
        Response response = given().contentType(ContentType.JSON).                       // accept type is json
                pathParam("id", 100).    // request with path param id=100
                when().get("/employees/{id}");// when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        // links.href[0] --> in the json file, find key links, then find its children href and get the first one
        String link = jsonPath.getString("links.href[0]");
        assertThat(link, equalTo("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees/100"));

    }


}
