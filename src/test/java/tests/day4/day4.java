package tests.day4;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class day4 {

    /**
     * WARMUP
     * when user makes get request to "/employees" in hr ORDS-HR api
     * then user verifies that status code is 200
     * and  user verifies that average salary is greater that 5000
     **/

    @Test
    public void test() {
        Response response = when().get("http://ec2-34-201-69-55.compute-1.amazonaws.com:1000/ords/hr/employees");
        response.then().assertThat().statusCode(200);
        List<Integer> salaryList = response.jsonPath().getList("items.salary");

        // how to get the average from a list of ints
        // get the sum
        int total = 0;
        for (Integer salary : salaryList) {
            total = total+salary;
        }
        // divide by the number of salaries
        // salaryList.size() --> gives the total number of salaries
        int average = total/salaryList.size();

        assertThat(average, greaterThan(5000));




    }

}
