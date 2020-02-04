package tests.day8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.ConfigurationReader;
import utilities.TokenUtility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static utilities.TokenUtility.UserType.*;

// when we add this annotation, we dont have to make the beforeall method static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookitStudentTests {

    @BeforeAll
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("bookitQa1Url");
    }

    /**
     * get all students from the  /api/students endpoint
     * using token from a team member
     * verify status code 200
     * verify data type json
     */
    @Test
    public void testAllStudents(){

        // get a token
        String token = TokenUtility.getToken(TEAM_MEMBER);
        // get all students
        assertThat(token, not(emptyOrNullString()));
    }
}
