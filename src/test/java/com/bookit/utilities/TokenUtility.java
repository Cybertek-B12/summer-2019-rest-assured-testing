package com.bookit.utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenUtility {

    public enum UserType{TEACHER, TEAM_LEADER, TEAM_MEMBER};


    public static String getToken(UserType type) {
        String token=null, email=null, password = null;

        switch (type) {
            case TEACHER:
                email = Environment.TEACHER_USERNAME;
                password = Environment.TEACHER_PASSWORD;
                break;
            case TEAM_LEADER:
                email = Environment.LEADER_USERNAME;
                password = Environment.LEADER_PASSWORD;
                break;
            case TEAM_MEMBER:
                email = Environment.MEMBER_USERNAME;
                password = Environment.MEMBER_PASSWORD;
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
