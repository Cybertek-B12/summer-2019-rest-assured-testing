package com.bookit.utilities;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUtility {

    // method to get user email and password

    /**
     * returns email and password of a user based on role and location
     *
     * @param role
     * @param location
     * @return
     */
    public static Map<String, String> getUserCredentials(String role, String location) {
        // point to the correct file path based on location argument
        String file = null;
        switch (location) {
            case ApplicationConstants.IL:
                file = "./src/test/resources/test_data/il-users.xlsx";
                break;
            case ApplicationConstants.DARK_SIDE:
                file = "./src/test/resources/test_data/dark-side-users.xlsx";
                break;
            case ApplicationConstants.LIGHT_SIDE:
                file = "./src/test/resources/test_data/light-side-users.xlsx";
                break;
        }

        // read the file based on filepath and get the data
        List<Map<String, String>> userData = new ExcelUtil(file, "Sheet1").getDataList();

        for (Map<String, String> user : userData) {
            if (role.contains(user.get("role"))) {
                Map<String, String> map = new HashMap<>();
                map.put("email", user.get("email"));
                map.put("password", user.get("first_name").toLowerCase() + user.get("last_name").toLowerCase());
                return map;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(
                getUserCredentials( ApplicationConstants.STUDENT_TEAM_LEADER,
                ApplicationConstants.LIGHT_SIDE));
    }

    /**
     *
     */
    public static Map<String, Object> getNewStudent(){
        Map<String, Object> student = new HashMap<>();
        Faker faker = new Faker();

        student.put("first-name", faker.name().firstName());
        student.put("last-name", faker.name().lastName());
        student.put("email", faker.internet().emailAddress());
        student.put("password", "password");
        student.put("role", "student-team-member");
        student.put("campus-location", "VA");
        student.put("batch-number", 12);
        student.put("team-name", "Online_Hackers");

        return student;
    }

}




