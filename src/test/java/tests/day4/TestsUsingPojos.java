package tests.day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Job;

import java.io.*;
import java.util.List;

public class TestsUsingPojos {

    @BeforeAll
    public static void setup() {
        baseURI = "http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr";
    }

    @Test
    public void getASingleJob() {
        Response response = given().pathParam("id", "IT_PROG").
                when().get("/jobs/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
        // convert the body into given type
        // desrialzaiotn is happening here. from json we get jobs
        Job itJob = response.as(Job.class);
        System.out.println(itJob);

        System.out.println("-------------------");
        System.out.println("itJob.getJob_title() = " + itJob.getJob_title());
        System.out.println("itJob.getJob_id() = " + itJob.getJob_id());

        // veirfy that job title is programmer
        assertThat(itJob.getJob_title(), equalTo("Programmer"));

    }

    @Test
    public void test() {
//        Job job = new Job("01", "accountant", 100, 100000);
//        System.outt.println(job);

    }


    @Test
    public void getManyJobs() {
        // get the response that contains all the jobs
        Response response = when().get("/jobs");
        response.then().statusCode(200);
//        response.prettyPrint();

        // "items" --> write jsonpath that matches all the jobs in the response
        // Job.class  --> which pojo class do you want to convert to
        // convert the values to job object
        // put all the job objects into the new list
        // list contains Job type
        // desrialzaiotn is happening here. from json we get jobs
        List<Job> jobs = response.jsonPath().getList("items", Job.class);
        System.out.println(jobs.get(0).getJob_title());
        System.out.println(jobs.get(1).getMin_salary());
// mizgin wants to do items. but did not use the mic
//        ok lets do after the break.
        // lunch is until 2.10
    }

}
