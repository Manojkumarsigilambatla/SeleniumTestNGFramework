package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="cucumber\\stepdefinitions",monochrome=true,plugin= {"pretty","html:target/index.html"}
,dryRun = false,tags ="@EndToEnd")
public class TestRunner extends AbstractTestNGCucumberTests{
	
	

}
