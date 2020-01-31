package tests.day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Address;
import pojos.Company;
import pojos.Contact;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;

public class CompanyTrainingAPITest {

  @BeforeAll
    public static void setup(){
      RestAssured.baseURI = ConfigurationReader.get("companyAPiBaseURL");
  }

    /**
     *
     *
     * post a new student using url /student/create
     *
     * verify success message
     */
  @Test
    public void postAStudentTest(){
      // create a student pojo will all required info
      Address address = new Address(1312, "123 Some st", "Arlington", "VA", 22221);
    Company company = new Company(312, "Foxborn Productions",
            "Assistant Deputy Vice President", "12/26/2019", address);
    //int contactId, String emailAddress, String phone, String permanentAddress
    Contact contact = new Contact(4234, "advp@foxbornproductions.com",
            "234242343", "123 Same st, Arlington, VA, 22221");




      given().contentType(ContentType.JSON).accept(ContentType.JSON).
              body(null);


  }

}
