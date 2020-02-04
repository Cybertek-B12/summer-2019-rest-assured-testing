package day5;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import day5.school.Address;
import day5.school.Company;
import day5.school.Contact;
import day5.school.Student;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolPractice {
    @BeforeAll
    public static void setup() {
        baseURI = "http://api.cybertektraining.com/";
    }

    /**
     * WARMUP
     * given path parameter is "/students"
     * when user makes get request
     * then user verifies that status code is 200
     * and user verifies that first student has firstName Jordan
     */

    @Test
    @DisplayName("Verifies that first student has firstName Jordan")
    public void test1(){
        given().
                accept(ContentType.JSON).
                when().
                get("student/all").prettyPeek();
//                then().assertThat().body("students[0].firstName", is("Jordan"));
    }

    /**
     * WARMUP
     * given path parameter is "/students"
     * when user makes get request
     * then user verifies that status code is 200
     * and user verifies that first student has firstName Jordan
     * *date format: MM/dd/yyyy
     */

    @Test
    @DisplayName("Verifies that Jordan's joinDate is before todays date")
    public void test2(){

        Response response = given().
                accept(ContentType.JSON).
                when().
                get("student/all");
        String date = response.jsonPath().
                getString("students.findAll {it.firstName == \"Jordan\"}.joinDate").
                replace("[","").replace("]", "");

        System.out.println("Date: "+date);

        LocalDate studentsJoinDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        assertTrue(studentsJoinDate.isBefore(LocalDate.now()));
    }
    @Test
    @DisplayName("Create student with string looks sooooo bad")
    public void test3_1(){
        String jsonBody = "{\n" +
                "    \"admissionNo\": \"9988\",\n" +
                "    \"batch\": \"10\",\n" +
                "    \"birthDate\": \"01/06/1991\",\n" +
                "    \"company\": {\n" +
                "        \"address\": {\n" +
                "            \"city\": \"McLean\",\n" +
                "            \"state\": \"Virginia\",\n" +
                "            \"street\": \"7925 Jones Branch Dr #3300\",\n" +
                "            \"zipCode\": \"22102\"\n" +
                "        },\n" +
                "        \"companyName\": \"Cybertek\",\n" +
                "        \"startDate\": \"02/02/2020\",\n" +
                "        \"title\": \"SDET\"\n" +
                "    },\n" +
                "    \"contact\": {\n" +
                "        \"emailAddress\": \"sdet@email.com\",\n" +
                "        \"phone\": \"404-323-2341\",\n" +
                "        \"premanentAddress\": \"7925 Jones Branch Dr #3300\"\n" +
                "    },\n" +
                "    \"firstName\": \"Roberto\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"joinDate\": \"11/26/2017\",\n" +
                "    \"lastName\": \"D'Vinci\",\n" +
                "    \"major\": \"MBA\",\n" +
                "    \"password\": \"secret\",\n" +
                "    \"section\": \"AP-45\",\n" +
                "    \"subject\": \"Math\"\n" +
                "}";
        Gson gson = new Gson();

        Response response = given().
                                body(jsonBody).
                                contentType(ContentType.JSON).
                            post("/student/create").
                                prettyPeek();
    }

    @Test
    @DisplayName("Create student with POJO")
    public void test3(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String email = faker.internet().safeEmailAddress();
        Address address = new Address("McLean", "Virginia", "7925 Jones Branch Dr #3300", 7925);
        Company company = new Company(address, "02/02/2020", "Cybertek", "SDET");
        Contact contact = new Contact(email, "454-323-2341", "7925 Jones Branch Dr #3300");

        Student student = new Student(12342256, 10, "01/07/1990", company, contact,
                                        firstName, "Male", "11/21/2017",
                                        lastName, "CKJ","AP45", "11", "Science");
        Response response = given().log().body(true).
                                body(student).
                                accept(ContentType.JSON).
                                contentType(ContentType.JSON).
                            when().
                                post("/student/create").
                                prettyPeek();
        assertEquals(200, response.getStatusCode());

        int studentId = response.jsonPath().getInt("studentId");



        given().
                accept(ContentType.JSON).
        when().
                delete("/student/delete/{id}", studentId).
                prettyPeek();


    }
}
