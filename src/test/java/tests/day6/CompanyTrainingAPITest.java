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
        Address address = new Address(1312, "123 Some st", "Arlington", "VA", 22221);
        Company company = new Company(312, "Foxborn Productions",
                "Assistant Deputy Vice President", "12/26/2019", address);
        Contact contact = new Contact(4234, "advp@foxbornproductions.com",
                "234242343", "123 Same st, Arlington, VA, 22221");

        Student student = new Student(2134, "John", "Doe", 1, "12/26/2009", "12/26/1990",
                "123abc", "Java", "Male", "324234", "Programming", "section 39",
                contact, company);

      System.out.println("student = " + student);
//        given().contentType(ContentType.JSON).accept(ContentType.JSON).
//                body(null);


    }

}
