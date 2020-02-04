package tests.testing;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSTActions {

    /*
        Given accept type and Content type is JSON
        And request json body is:
        {
          "gender":"Male",
          "name":"Maximus",
          "phone":8877445596
       }
        When user sends POST request to '/spartans/'
        Then status code 201
        And content type should be application/json
        And json payload/response should contain:
        "A Spartan is Born!" message
        and same data what is posted
     */

    @Test
    public void post_new_spartan_test() {
         Response response = given().accept(ContentType.JSON)
                             .and().contentType(ContentType.JSON)
                              .and().body("{\"gender\":\"Male\",\n" +
                              "\"name\":\"POST TEST\",\n" +
                               "\"phone\":8877445596}")
                                .when().post("http://54.164.195.86:8000/api/spartans/");

         //response validations:
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");

        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);

        assertEquals("A Spartan is Born!", message1);
        assertEquals("A Spartan is Born!", message2);
        //assert name, gender, phone

        assertEquals("Male", json.getString("data.gender"));
        assertEquals("POST TEST", json.getString("data.name"));
        assertEquals(8877445596L, json.getLong("data.phone"));

    }

    @Test
    public void post_new_spartan_with_map_test() {
        //Create a map to be used as a body for post request
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Female");
        requestMap.put("name" , "Helen");
        requestMap.put("phone" , 3451234567L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("http://54.164.195.86:8000/api/spartans/");

        //response validations:
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");

        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);

        assertEquals("A Spartan is Born!", message1);
        assertEquals("A Spartan is Born!", message2);
        //assert name, gender, phone

        assertEquals("Female", json.getString("data.gender"));
        assertEquals("Helen", json.getString("data.name"));
        assertEquals(3451234567L, json.getLong("data.phone"));

        int spartanID = json.getInt("data.id");
        System.out.println("spartanID = " + spartanID);
        System.out.println("SENDING GET REQUEST WITH SPARTAN ID: " + spartanID);

        get("http://54.164.195.86:8000/api/spartans/" + spartanID).body().prettyPrint();

    }

    @Test
    public void post_a_new_spartan_with_pojo_object() {
        //Create a spartan object to be used as a body for post request
        Spartan spartan = new Spartan();
        spartan.setGender("Male");
        spartan.setName("PostSpartan");
        spartan.setPhone(5431234577L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("http://54.164.195.86:8000/api/spartans/");

        //response validations:
        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");

        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);

        assertEquals("A Spartan is Born!", message1);
        assertEquals("A Spartan is Born!", message2);
        //assert name, gender, phone

        assertEquals("Male", json.getString("data.gender"));
        assertEquals("PostSpartan", json.getString("data.name"));
        assertEquals(5431234577L, json.getLong("data.phone"));

        int spartanID = json.getInt("data.id");
        System.out.println("spartanID = " + spartanID);
        System.out.println("SENDING GET REQUEST WITH SPARTAN ID: " + spartanID);
        get("http://54.164.195.86:8000/api/spartans/" + spartanID).body().prettyPrint();

    }

    public static String initCap(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    @Test
    public void post_a_new_spartan_with_pojo_object_from_uinames_api() {

        for (int i = 1; i <=3; i++) {
               //send get request to http://uinames.com/api/ and extract name and gender

           JsonPath uinamesJson = get("http://uinames.com/api/").body().jsonPath();

//{"name":"Danica","surname":"Baša","gender":"female","region":"Slovakia"}

           //Create a spartan object to be used as a body for post request
           Spartan spartan = new Spartan();
           spartan.setGender(initCap(uinamesJson.getString("gender")));
           spartan.setName(uinamesJson.getString("name") + " " + uinamesJson.getString("surname"));
           spartan.setPhone(5431234577L);

           Response response = given().accept(ContentType.JSON)
                   .and().contentType(ContentType.JSON)
                   .and().body(spartan)
                   .when().post("http://54.164.195.86:8000/api/spartans/");

           response.prettyPrint();

           //response validations:
           assertEquals(201, response.statusCode());
           assertEquals("application/json", response.contentType());
           //extract message using path method
           String message1 = response.path("success");
           //extract message using jsonpath
           JsonPath json = response.jsonPath();
           String message2 = json.getString("success");

           System.out.println("message1 = " + message1);
           System.out.println("message2 = " + message2);

           assertEquals("A Spartan is Born!", message1);
           assertEquals("A Spartan is Born!", message2);
           //assert name, gender, phone

//        assertEquals("Male", json.getString("data.gender"));
//        assertEquals("PostSpartan", json.getString("data.name"));
//        assertEquals(5431234577L, json.getLong("data.phone"));

           int spartanID = json.getInt("data.id");
           System.out.println("spartanID = " + spartanID);
           System.out.println("SENDING GET REQUEST WITH SPARTAN ID: " + spartanID);
           get("http://54.164.195.86:8000/api/spartans/" + spartanID).body().prettyPrint();
       }
    }


}
