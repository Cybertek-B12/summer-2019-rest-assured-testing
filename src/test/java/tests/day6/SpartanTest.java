package tests.day6;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Spartan;
import utilities.ConfigurationReader;
import utilities.SpartanApiUtils;

import static org.hamcrest.Matchers.equalTo;

public class SpartanTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = ConfigurationReader.get("spartanApiBaseURL");
    }

    @Test
    public void createSpartanTest(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = "Female";
        int phone = 2022323212;

        Spartan spartan = new Spartan(gender, name, phone);
        System.out.println(spartan);

        Response response = SpartanApiUtils.createSpartan(spartan);
        response.then().statusCode(201);
        response.then().body("data.name", equalTo(spartan.getName()));
    }

}
