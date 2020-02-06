package com.bookit.step_definitions;

import com.bookit.pages.SignInPage;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import com.bookit.utilities.TokenUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static com.bookit.utilities.TokenUtility.UserType.TEACHER;

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
        String name = (String) student.get("first-name") + " "+student.get("last-name");
        assertThat(response.asString(), containsString(name));
    }



    @Given("I should be able to login with same student information")
    public void i_should_be_able_to_login_with_same_student_information() {
        // go to login page
        Driver.get().get(Environment.URL);
        // login using student info
        SignInPage signInPage = new SignInPage();
        String email = (String) student.get("email");
        String password = (String) student.get("password");
        signInPage.login(email, password);
        // verify title
        assertThat(Driver.get().getCurrentUrl(), endsWith("map"));
    }

}
