package day7_put_delete_authorization_schemavalidation;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.http.ContentType;
import utils.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BookitApiAuthorization {
    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";
    @BeforeClass
    public static void setUp(){
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com/api";
    }

    @Test
    public void getAllcampuses_using_access_token() {
        Response response = given().header("Authorization", accessToken)
                            .accept(ContentType.JSON)
                            .when().get("/campuses");
        
        //using JSONPath print name of room id 111 in light side
        JsonPath jsonPath = response.jsonPath();
        
        String roomName = jsonPath.getString("clusters[0].rooms[0].name[0]");
        System.out.println("roomName = " + roomName);

    }

}
