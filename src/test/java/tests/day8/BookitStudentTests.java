package tests.day8;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.ConfigurationReader;
import utilities.TokenUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
        assertThat(token, not(emptyOrNullString()));
        // get all students
        System.out.println(token);
       Response response = given().
                header("Authorization", token).
        when().
                get("/api/students");
        response.prettyPeek();
        response.then().
                    statusCode(200).
                    contentType(ContentType.JSON);
        List<String> firstNames = response.jsonPath().getList("firstName");
        System.out.println("firstNames.size() = " + firstNames.size());

    }

    /*
    Try to create new student using the post method to /api/students/student
    by using the token of a team member
    verify status code 403
    verify error message only teacher allowed to modify database
     */
    @Test
    public void testTeamMember(){
        // create a map that contains key / values which represents a new student info
        Map<String, Object> newStudent = getNewStudent();
        System.out.println(newStudent);
        // get token for a team member
        String token = TokenUtility.getToken(TEAM_MEMBER);
        // send query by passing the stydent map as query param
        // and the team member token
        given().
                header("Authorization", token).
                queryParams(newStudent).
        when().
                post("/api/students/student").
                prettyPeek().
        then().
                statusCode(403).
                body(containsString("only teacher allowed to modify database."));

    }

    public Map<String, Object> getNewStudent(){
        Map<String, Object> student = new HashMap<>();
        Faker faker = new Faker();

        student.put("first-name", faker.name().firstName());
        student.put("last-name", faker.name().lastName());
        student.put("email", faker.internet().emailAddress());
        student.put("password", "password");
        student.put("role", "student-team-member");
        student.put("campus-location", "VA");
        student.put("batch-number", 12);
        student.put("team-name", "bestteam");

        return student;
    }

    /*
    Try to create new student using the post method to /api/students/student
    by using the token of a team leader
    verify status code 403
    verify error message only teacher allowed to modify database
     */
    @Test
    public void testTeamLeader(){
        Map<String, Object> newStudent = getNewStudent();
        String token = TokenUtility.getToken(TEAM_LEADER);

        given().
                header("Authorization", token).
                params(newStudent).
        when().
                post("/api/students/student").
                prettyPeek().
        then().
                statusCode(403).
                body(containsString("only teacher allowed to modify database."));


    }

    /**
     * Create new student using the post method to /api/students/student
     * by using the token of a teacher
     * verify status code 201
     * verify error message only teacher allowed to modify database
     */
    @Test
    public void testTeacher(){
        Map<String, Object> newStudent = getNewStudent();
        String token = TokenUtility.getToken(TEACHER);

        given().log().all().
                header("Authorization", token).
                params(newStudent).
        when().
                post("api/students/student").
                prettyPeek().
        then().
                statusCode(201).
                body(endsWith("has been added to database."));

    }



}
