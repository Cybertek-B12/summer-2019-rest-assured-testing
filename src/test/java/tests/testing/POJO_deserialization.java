package tests.testing;

import com.google.gson.Gson;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POJO_deserialization {

    @Test
    public void spartan_to_pojo_object_deserialization() {
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.164.195.86:8000/api/spartans/15");
//        System.out.println(response.statusCode());
//        System.out.println(response.headers());

        response.prettyPrint();


//
//        //deserialize json to pojo java object.
//        //JSON response body >>> Custom Java class object
        // taking the body of the response and converting to object
        Spartan spartan = response.body().as(Spartan.class);

//
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getSpartanID());
        System.out.println(spartan.getPhone());

        System.out.println("spartan.toString() = " + spartan.toString());
//
        assertEquals("Meta", spartan.getName());
        assertEquals("Female", spartan.getGender());
        assertEquals(new Integer(15), spartan.getSpartanID());
        assertEquals(new Long(1938695106), spartan.getPhone());

    }

    @Test
    public void gSonExample() {

        Spartan spartan = new Spartan(20, "Vlad", "male", 7033964165L);

        Gson gson = new Gson();
        //Serialize spartan object to JSON format using GSON
        String json = gson.toJson(spartan);
        System.out.println(json);

        String myJson = "{\"id\":25,\"name\":\"Roman\",\"gender\":\"male\",\"phone\":5712223366}";
        //Deserialization. Convert JSON to Java Spartan object
        Spartan Roman = gson.fromJson(myJson,Spartan.class);
        System.out.println(Roman.toString());

       //fromJson(String json, Which.class) --> it will convert the json to object of the class
        //toJson(java object) -> it will take the java object and create json and return it
    }

    @Test
    public void list_of_spartans_pojo_deserialization() {
        Response response = given().accept(ContentType.JSON)
                .when().get("http://54.164.195.86:8000/api/spartans/");

        List<Spartan> allSpartans = response.body().as(new TypeRef<List<Spartan>>(){});

        System.out.println(allSpartans.get(0));

        Spartan first = allSpartans.get(0);
        System.out.println(first.toString());

        for (Spartan sp : allSpartans) {
            System.out.println(sp.toString());
        }

        System.out.println("######### AllSpartans class ############");
        //USING ALLSPARTANS class for deserialization
        //TODO: fix the deserialization issue
        AllSpartans allSpartansObj = response.body().as(AllSpartans.class);

        System.out.println(allSpartansObj.getSpartanList().get(0).toString());



    }



}


















