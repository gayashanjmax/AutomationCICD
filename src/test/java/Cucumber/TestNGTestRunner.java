package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="gayashanhub.stepDefinitions",////to run cucumber options in the TestNG runner. And need to give the location of the feature file and stepDefinition 
monochrome=true, tags = "@Regression", plugin= {"html:taget/cucumber.html"}) //and use monochrome to use the report to readable format . tags attribute use to group the tests
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ //use AbstractTestNGCucumberTests to help cucumber to run TestNG commands

}
