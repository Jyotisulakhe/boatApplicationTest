package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\Asus\\Documents\\workspace-spring-tool-suite-4-4.22.0.RELEASE\\boatTesting\\src\\test\\resources\\boat\\boatTest.feature",
		glue = "steps",
		plugin = {"pretty", "html:target/cucumber-reports.html"}
		)



public class Testrunner {
	
	

}
