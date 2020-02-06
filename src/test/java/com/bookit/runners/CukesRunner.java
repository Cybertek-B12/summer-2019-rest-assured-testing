package com.bookit.runners;


import cucumber.api.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
             "html:target/default-cucumber-reports",
              "json:target//json-reports/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "com/bookit/step_definitions"
        , dryRun = false
        , tags = "@leader"
)
public class CukesRunner {
}
