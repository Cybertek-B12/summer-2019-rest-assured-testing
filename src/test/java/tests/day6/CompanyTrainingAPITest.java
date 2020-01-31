package tests.day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Address;
import pojos.Company;
import pojos.Contact;
import pojos.Student;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;

public class CompanyTrainingAPITest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigurationReader.get("companyAPiBaseURL");
    }

    /**
     * post a new student using url /student/create
     * <p>
     * verify success message
     */
    @Test
    public void postAStudentTest() {
        // create a student pojo will all required info
        Address address = new Address( "123 Some st", "Arlington", "VA", 22221);
        Company company = new Company("Foxborn Productions",
                "Assistant Deputy Vice President", "12/26/2019", address);
        Contact contact = new Contact( "advp@foxbornproductions.com",
                "234242343", "123 Same st, Arlington, VA, 22221");

        Student student = new Student( "John", "Doe", 1, "12/26/2009", "12/26/1990",
                "123abc", "Java", "Male", "324234", "Programming", "section 39",
                contact, company);



        given().log().everything()
                .contentType(ContentType.JSON).accept(ContentType.JSON).
                body(student).
            when().post("/student/create").
                prettyPeek().
            then().statusCode(200);


    }

    @Test
    public void justAtest(){

        // http://www.omdbapi.com/?t
        //=SomeMovie&apikey=sdfssdgdsfgsdfgdfgsd


//        given().queryParam("apiKey", "lkasdhfasdhlkf").
//                queryParam("t", "SOmeMovie").
//                get("ASfasd")

        given().header("x-api-key", "90896162-e3ef-4f00-9e9f-6ac4f1228ea2").
                when().get("https://api.thecatapi.com/v1/images/search")
                .prettyPeek();
    }

}
