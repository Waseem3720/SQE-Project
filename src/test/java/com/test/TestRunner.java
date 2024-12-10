package com.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features",           // Folder name for feature files
        glue = {"stepDefination"},       // Package name for step definitions
        plugin = {
                "pretty",                         // Console readable output
                "html:target/cucumber-reports.html",  // HTML report
                "junit:target/cucumber-reports.xml",   // JUnit XML report
                "json:target/cucumber-reports.json"  // JSON report
        },
        monochrome = true                          // Makes console output readable
)
public class TestRunner {
}

