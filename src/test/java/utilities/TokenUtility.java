package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenUtility {

    public static String getTeacherToken(String type) {
        String token=null, email=null, password = null;

        switch (type.toLowerCase()) {
            case "teacher":
                email = ConfigurationReader.get("teacher_email");
                password = ConfigurationReader.get("teacher_password");
                break;
            case "team_leader":
                email = ConfigurationReader.get("team_leader_email");
                password = ConfigurationReader.get("team_leader_password");
                break;
            case "team_member":
                email = ConfigurationReader.get("team_member_email");
                password = ConfigurationReader.get("team_member_password");
                break;
        }
        Response response = given().
                               queryParam("email", email).
                                queryParam("password", password).
                             when().get("/sign");
        response.then().statusCode(200);
        token = response.path("accessToken");
        return token;

    }


}
