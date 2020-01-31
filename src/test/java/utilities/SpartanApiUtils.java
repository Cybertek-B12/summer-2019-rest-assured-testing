package utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Spartan;

import static io.restassured.RestAssured.given;

public class SpartanApiUtils {

    /**
     * Create a new spartan by sending a request the spartan API
     * pass the given spartan parameter as the request body.
     * return the response object. response must contain json payload.
     *
     * @param spartan
     * @return response
     */
    public static Response createSpartan(Spartan spartan) {
        Response response = given().log().everything().
                                contentType(ContentType.JSON).
                                body(spartan).
                        when().post("/api/spartans").
                                prettyPeek();
        return response;
    }

    public static Spartan createSpartanObject(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = "Female";
        int phone = 2022323212;

        Spartan spartan = new Spartan(gender, name, phone);
        return  spartan;
    }

}
