package com.bookit.step_definitions;

import com.bookit.utilities.TokenUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.bookit.utilities.TokenUtility.UserType.TEACHER;
import static io.restassured.RestAssured.given;

public class NewStudentStepDefinitions extends Base{

    private static final Logger logger = LogManager.getLogger(NewStudentStepDefinitions.class);

    @Given("I have a token as a teacher")
    public void i_have_a_token_as_a_teacher() {
        String token = TokenUtility.getToken(TEACHER);
        request = given().log().all()
                .header("Authorization", token);
        logger.info(token);
    }

    @Then("the response should contain student name")
    public void the_response_should_contain_student_name() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

}
