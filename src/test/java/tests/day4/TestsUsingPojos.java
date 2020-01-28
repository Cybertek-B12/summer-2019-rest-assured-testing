package tests.day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Job;

public class TestsUsingPojos {

    @BeforeAll
    public static void setup(){
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }

    @Test
    public void getASingle(){
        Response response = given().pathParam("id", "IT_PROG").
                            when().get("/jobs/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
        // convert the body into given type
        Job itJob = response.as(Job.class);
        System.out.println(itJob);

        System.out.println("-------------------");
        System.out.println("itJob.getJob_title() = " + itJob.getJob_title());
        System.out.println("itJob.getJob_id() = " + itJob.getJob_id());

        // veirfy that job title is programmer
        assertThat(itJob.getJob_title(), equalTo("Programmer"));

    }
}
