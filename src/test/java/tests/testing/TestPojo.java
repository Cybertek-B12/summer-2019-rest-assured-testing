package tests.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPojo {

    @BeforeAll
    public static void setUp() {
        baseURI = "Job.java\n" +
                "Location.java\n" +
                "Employee.java\n" +
                "TestingPojos.java/ords/hr";
    }

    @Test
    public void test1(){
//        Region region = given().pathParam("id", 1)
//                .when().get("/regions/{id}").body().as(Region.class);
//
//        System.out.println(region);
//        System.out.println("region = " + region.getRegion_id());
//        System.out.println("region.getRegion_name() = " + region.getRegion_name());


    }
}
