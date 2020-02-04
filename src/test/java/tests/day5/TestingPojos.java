package day5;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestingPojos {

    @BeforeAll
    public static void setup() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }

    //accept("application/json") shortcut for header("Accept", "application/json")
    //we are asking for json as a response


    /**
     * WARMUP
     * given path parameter is "/employees"
     * when user makes get request
     * then user verifies that status code is 200
     * and user verifies that average salary is grater that 5000
     */

    @Test
    @DisplayName("Verify that average salary is grater that 5000")
    public void test1() {
        Response response = given().
                accept("application/json").
                when().
                get("/employees");

        List<Integer> salaries = response.jsonPath().getList("items.salary");
        int sum = 0;
        for (int salary : salaries) {
            sum += salary;
        }
        int avg = sum / salaries.size();

        System.out.println("Avg: " + avg);

        assertTrue(avg > 5000);

    }

    /**
     * Given accept type is JSON
     * When users sends a GET request to "/employees/101"
     * Then user converts JSON into POJO
     */
    @Test
    @DisplayName("GET employee 101 and convert into java object")
    public void test10() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/employees/{id}", 101).prettyPeek();
        Employee e = response.as(Employee.class);
        System.out.println(e);

    }


    /**
     * Given accept type is JSON
     * When users sends a GET request to "/employees"
     * Then user converts JSON into Collection of POJOs
     */
    @Test
    @DisplayName("GET all employees and convert them into collection of java objects")
    public void test11() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/employees").prettyPeek();
        List<Employee> employees = response.jsonPath().getList("items", Employee.class);

        for (Employee e : employees) {
            System.out.println(e);
        }

    }

    /**
     * #TASK
     * Given accept type is JSON
     * When users sends a GET request to "/jobs"
     * Then user converts 1st JSON object into POJO
     */
    @Test
    @DisplayName("GET 1st job and convert into java object")
    public void test12() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/jobs").prettyPeek();
        Job job = response.jsonPath().getObject("items[0]", Job.class);
        System.out.println(job);

    }

    /**
     * Given accept type is JSON
     * When users sends a GET request to "/jobs"
     * Then user converts JSON into Collection of POJOs
     */
    @Test
    @DisplayName("GET all jobs and convert them into collection of java objects")
    public void test13() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/jobs");

        List<Job> employees = response.jsonPath().getList("items", Job.class);

        for (Job job : employees) {
            System.out.println(job);
        }

    }

    /**
     * #TASK
     * Given accept type is JSON
     * When users sends a GET request to "/locations"
     * Then user converts 1st JSON object into POJO
     */
    @Test
    @DisplayName("GET 1st location object and convert into java object")
    public void test14() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/locations");

        Location location = response.jsonPath().getObject("items[0]", Location.class);
        System.out.println(location);

    }

    /**
     * Given accept type is JSON
     * When users sends a GET request to "/locations"
     * Then user converts JSON into Collection of POJOs
     */
    @Test
    @DisplayName("GET all locations and convert them into collection of java objects")
    public void test15() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/locations");

        List<Location> locations = response.jsonPath().getList("items", Location.class);

        for (Location location : locations) {
            System.out.println(location);
        }

    }

//    /**
//     * Given accept type is JSON
//     * When users sends a POST request to "/jobs"
//     * Then user post new job with following info
//     *  |job_id |job_title                              |min_salary|max_salary|
//     *  |SF_SDET|Software Development Engineer in Test  | 3000     |15000     |
//     * And user verifies that job has been posted successfully
//     *  |job_id |job_title                              |min_salary|max_salary|
//     *  |SF_SDET|Software Development Engineer in Test  | 3000     |15000     |
//     */
//    @Test
//    @DisplayName("POST new job")
//    public void test16() {
//        Job sdetJob = new Job("SF_SDET", "Software Development Engineer in Test", 3000, 15000);
//        Gson gson = new Gson();
//        String js = gson.toJson(sdetJob);
//        System.out.println(js);
//        Response response = given().
//                accept(ContentType.JSON).
//                when().
//                post("/jobs", js);
//
//        System.out.println(response.statusCode());
//        List<Job> employees = response.jsonPath().getList("items", Job.class);
//
//        for (Job job : employees) {
//            System.out.println(job);
//        }
//
//    }

}
