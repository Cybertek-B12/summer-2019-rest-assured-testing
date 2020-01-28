package tests.day4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Employee;
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
        System.out.println("Number of total jobs: " + jobs.size());
        System.out.println(jobs.get(1).getJob_title());

        Job itJob = response.jsonPath().getObject("items[0]", Job.class);

        System.out.println(itJob);

    }

    @Test
    public void getOneEmployee(){
        Response response = given().pathParam("id", "102").
                when().get("/employees/{id}");
//        response.prettyPrint();
        Employee emp = response.as(Employee.class);
        System.out.println(emp);

        //verify taht first name is lex
        assertThat(emp.getFirstName(), equalTo("Lex"));
    }


}
