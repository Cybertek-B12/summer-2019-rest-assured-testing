package tests.day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class JsonPathTests {

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
                .when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}")
                .prettyPeek()
                .then().assertThat().statusCode(200)
                .and().assertThat().body("region_id",equalTo(1))
                .and().assertThat().body("region_name",equalTo("Europe"));
    }


    @Test
    public void validateRegionNameTest1(){
        Response response = given().pathParam("id",1)
                .when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/regions/{id}");
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.getString("region_id");
        assertThat(id, equalTo("1"));
        String name = jsonPath.getString("region_name");
        assertThat(name, equalTo("Europe"));

    }
}
