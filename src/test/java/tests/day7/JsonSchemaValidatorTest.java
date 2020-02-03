package tests.day7;

import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidatorTest {

    /**
     * call the company training api
     * get teacher by id 101
     * verify that matches the given schema
     * schema is in resources folder.
     * name: teacher_spartan.json
     */
    @Test
    public void testSchema(){
        /// picks up the fiel from the resources folder
        given().pathParam("id", 101).

            when().get(ConfigurationReader.get("companyAPiBaseURL")+"teacher/{id}").
                prettyPeek().
                then().statusCode(200).
                body(matchesJsonSchemaInClasspath("teacher_template.json"));
    }

}
