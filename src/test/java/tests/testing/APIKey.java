package com.automation.tests.day8;

import com.automation.pojos.Country;
import org.junit.jupiter.api.BeforeAll;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class APIKey {
    //WEBSITE https://calendarific.com/account | Limit is 100 requests with given API key

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("calendarific.uri");
    }

    /**
     * Given accept content type as JSON
     * When user sends GET request to "/countries"
     * Then user verifies that status code is 401
     * And user verifies that status line contains "Unauthorized" message
     * And user verifies that meta.error_detail contains "Missing or invalid api credentials." message
     */

    @Test
    @DisplayName("Access without API key, verify status code 401 Unauthorized")
    public void test1() {
        given().
                accept(ContentType.JSON).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().
                statusCode(401).
                statusLine(containsString("Unauthorized")).
                body("meta.error_detail", containsString("Missing or invalid api credentials."));

    }

    /**
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then user verifies that status code is 200
     * And user verifies that status line contains "OK" message
     * And user verifies that countries array is not empty
     */

    @Test
    @DisplayName("Access with API key, verify status code 200 OK and countries list is not empty")
    public void test2() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().
                statusCode(200).
                statusLine(containsString("OK")).
                body("response.countries", not(empty()));

    }

    /**
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then verify that total_holidays in United States is 418
     */

    @Test
    @DisplayName("Verify that total number of holidays in the US is equals to 418")
    public void test3() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().body("response.countries.find {it.country_name == 'United States' }.total_holidays", is(418));

        //find - find one property
        // findAll - will return list of found values
        //it.country_name == 'United States' - find JSON object where country_name = United States
        //}.total_holidays get total_holidays value from that JSON object
    }

    /**
     *  #######TASK########
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then user verifies that total number of holidays in United Kingdom is equals to 95
     */

    @Test
    @DisplayName("Verify that total number of holidays in United Kingdom is equals to 95")
    public void test3_1() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().body("response.countries.find {it.country_name == 'United Kingdom' }.total_holidays", is(95));
    }

    /**
     *  #######TASK########
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then user verifies that number of supported_languages in Spain is equals to 3
     */
    @Test
    @DisplayName("Verify that total number of supported languages in Spain is equals to 3")
    public void test3_2() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().body("response.countries.find {it.country_name == 'Spain' }.supported_languages", is(3));
    }

    /**
     *  #######TASK########
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then user verifies that iso-3166 parameter for Germany is equals to DE
     */
    @Test
    @DisplayName("Verify that iso-3166 parameter for Germany is equals to DE")
    public void test3_3() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek().
                then().
                assertThat().body("response.countries.find {it.country_name == 'Germany' }.iso-3166", is("DE"));
    }

    /**
     *
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * And query parameter country is equals to US
     * And query parameter type is equals to national
     * And query parameter year is equals to 2019
     * When user sends GET request to "/holidays"
     * Then user verifies that number of national holidays in US is equals to 11
     */

    @Test
    @DisplayName("Verify that number of national holidays in the US is equals to 11")
    public void test4() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        Response response = given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                queryParam("country", "US").
                queryParam("type", "national").
                queryParam("year", 2019).

                when().
                get("/holidays").prettyPeek();
//        get collection of holidays
        List<Map<String, ?>> holidays = response.jsonPath().get("response.holidays");

        System.out.println("Size: "+holidays.size());

        assertEquals(11, holidays.size());
    }

    /**
     * #######TASK########
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * And query parameter country is equals to PL
     * And query parameter type is equals to national
     * And query parameter year is equals to 2019
     * When user sends GET request to "/holidays"
     * Then user verifies that number of national holidays in Poland is equals to 13
     */

    @Test
    @DisplayName("Verify that number of national holidays in Poland is equals to 13")
    public void test4_1() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        Response response = given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                queryParam("country", "PL").
                queryParam("type", "national").
                queryParam("year", 2019).

                when().
                get("/holidays").prettyPeek();
//        get collection of holidays
        List<Map<String, ?>> holidays = response.jsonPath().get("response.holidays");

        System.out.println("Size: "+holidays.size());

        assertEquals(13, holidays.size());
    }

    /**
     * Given accept content type as JSON
     * And query parameter api_key with valid API key
     * When user sends GET request to "/countries"
     * Then convert payload into list of Country POJOs
     */
    @Test
    @DisplayName("Parse countries into POJOs")
    public void test5() {
        String APIKey = "0d434ffc46d3957af435c32ade360a09991d6f23";

        Response response =   given().
                accept(ContentType.JSON).
                queryParam("api_key", APIKey).
                when().
                get("/countries").prettyPeek();
//        get collection of countries
        List<Country> countries = response.jsonPath().getList("response.countries", Country.class);

        for (Country country: countries){
            System.out.println(country);
        }

    }
}
