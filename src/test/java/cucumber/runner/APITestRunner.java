package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/CreateProduct.feature",glue="cucumber\\stepdefinitions",monochrome = true,dryRun = false,plugin ="pretty")
public class APITestRunner extends AbstractTestNGCucumberTests{

}
