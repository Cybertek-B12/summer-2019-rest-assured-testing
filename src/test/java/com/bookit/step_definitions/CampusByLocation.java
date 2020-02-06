package com.bookit.step_definitions;

import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import com.bookit.utilities.TokenUtility;
import com.bookit.utilities.UserUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Map;

import static com.bookit.utilities.TokenUtility.UserType.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class CampusByLocation extends Base {
    private static final Logger logger = LogManager.getLogger(CampusByLocation.class);

    @Given("I have a token as a student team member")
    public void i_have_a_token_as_a_student_team_member() {
        String token = TokenUtility.getToken(TEAM_MEMBER);
        request = given().log().all()
                .header("Authorization", token);
        logger.info(token);
    }

    @Given("request has the following {string} parameters")
    public void request_has_the_following_parameters(String paramType, Map<String, String> params) {
        if (paramType.equals("path")) {
            request.pathParams(params);
        } else {
            request.queryParams(params);
        }
    }

    @When("I send a {string} request to {string}")
    public void i_send_a_request_to(String method, String endpoint) {
        if (method.equals("get")) {
            System.out.println(endpoint);
            response = request.when().get(endpoint);
            response.then().log().everything();
        }
    }

    @Then("the response code should be {int}")
    public void the_response_code_should_be(Integer code) {
        response.prettyPrint();
        response.then().statusCode(code);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String expected) {
        assertThat(response.asString(), containsString(expected));
    }


    /**
     * this is just example. doing the same thing above without cucumber
     */
    @Test
    public void testTheSameThing() {
        RestAssured.baseURI = Environment.BASE_URI;
        String token = TokenUtility.getToken(TEAM_MEMBER);
        Response response =
                given().
                        header("Authorization", token).
                        pathParams("campus_location", "VA").
                        when().
                        get("/api/campuses/{campus_location}");
        response.
                then().
                statusCode(200).
                body(containsString("light-side"));


    }

    @Given("I post a new student using {string}")
    public void i_post_a_new_student_using(String endpoint) {
        // create a new student information
        Map<String, Object> student = UserUtility.getNewStudent();
        // send a post request
        response = request
                        .queryParams(student)
                    .when()
                        .post(endpoint);
    }


}
