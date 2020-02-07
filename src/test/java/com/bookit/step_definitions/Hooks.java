package com.bookit.step_definitions;

import com.bookit.utilities.DBUtils;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before("@ui")
    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    @Before("@ws")
    public void setUpWs() {
        RestAssured.baseURI = Environment.BASE_URI;
    }

    @Before("@db")
    public void setUpDB() {
        DBUtils.createConnection(Environment.DB_HOST, Environment.DB_USERNAME, Environment.DB_PASSWORD);
    }

    @After()
    public void tearDown(Scenario scenario) {

        List<String> tags = (List<String>) scenario.getSourceTagNames();

        System.out.println(tags);
        System.out.println(scenario.getName());
        // run this code only of we have ui related tag
        if (tags.contains("@ui")) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            Driver.closeDriver();
        }
        // run this code only of we have database related tag
        if (tags.contains("@db")) {
            DBUtils.destroy();
        }

    }


}