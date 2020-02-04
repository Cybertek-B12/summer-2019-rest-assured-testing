package tests.testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToCollections {

    @Test
    public void hrApiEmployee_jsondata_to_java_object(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("employee_id" , 105)
                .when().get("http://54.164.195.86:1000/ords/hr/employees/{employee_id}");

        response.prettyPrint();
        Map<String, ?> employeeMap = response.body().as(Map.class);

        System.out.println(employeeMap.toString());
        
        String firstName = employeeMap.get("first_name").toString();
        System.out.println("firstName = " + firstName);
        
        assertEquals("David", firstName);
        
        double salary = (Double)employeeMap.get("salary");
        System.out.println("salary = " + salary);

        assertEquals(4800.5, salary,0.5);
        //delta amount. if there Difference by delta amount or less,
        // values will be considered equal
    }
    //List<Map<String, Object>>
    @Test
    public void convertAll_spartans_to_list_of_maps(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.164.195.86:8000/api/spartans/");

        List<Map<String, ?>> jsonListOfMap = response.body().as(List.class);

        //print all data of first spartan
        System.out.println(jsonListOfMap.get(0));

        Map<String, ?> first = jsonListOfMap.get(0);
        System.out.println("first = " + first);

        System.out.println(first.get("name"));

        int counter = 1;
        for( Map<String, ?> spartan : jsonListOfMap ) {
            System.out.println(counter+ " - spartan = " + spartan);
            counter++;
        }

    }

    @Test
    public void regions_data_to_collections(){
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.164.195.86:1000/ords/hr/regions/");

        Map<String, ? > dataMap = response.body().as(Map.class);
        //System.out.println(dataMap);

        //extract Europe, Americas, Asia from the above map
        //System.out.println( dataMap.get("items") );
        List<Map<String, ?>> regionsList =  (List<Map<String, ?>>)dataMap.get("items");

        System.out.println( regionsList.get(0).get("region_name") ) ;
        System.out.println( regionsList.get(1).get("region_name") ) ;
        System.out.println( regionsList.get(2).get("region_name") ) ;
    }

}
