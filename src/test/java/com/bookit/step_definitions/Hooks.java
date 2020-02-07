package com.bookit.step_definitions;

import com.bookit.utilities.DBUtils;
import com.bookit.utilities.DbUtility;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@ui")
    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    @Before("@ws")
    public void setUpWs(){
        RestAssured.baseURI = Environment.BASE_URI;
    }

    @Before("@db")
    public void setUpDB(){
        DbUtility.createConnection(Environment.DB_HOST, Environment.DB_USERNAME, Environment.DB_PASSWORD);
    }

    @After("@ui or @db")
    public void tearDown(Scenario scenario) {
        System.out.println("I am reporting the results");
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        DBUtils.destroy();
        System.out.println("Closing driver");
        Driver.closeDriver();
    }


    public static void main(String[] args) {
        DbUtility.createConnection(Environment.DB_HOST, Environment.DB_USERNAME, Environment.DB_PASSWORD);
        String q = "Select * from team;";
        List<List<Object>> queryResultList = DbUtility.getQueryResultList(q);
        System.out.println(queryResultList);

        DBUtils.destroy();

    }
}