package com.revature.steps;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/*
    To run our Cucumber tests, we can create a test runner. This is where we tell JUnit and Cucumber to work together.
 */

@Suite // Tells JUnit this class is used as the entry point for our test suite
@IncludeEngines("cucumber") // Tells JUnit Cucumber will be running one or more of these tests
@SelectPackages("features") // Tells JUnit where to find out tests
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.revature.steps") // Tells Cucumber where to find our Glue steps
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty") // Makes the CLI test report much nicer to read
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:reports/cucumber-report.html") // Makes Cucumber generate an HTML report
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "junit:reports/cucumber-report.xml") // Makes Cucumber generate an HTML report
public class CucumberRunner {}
