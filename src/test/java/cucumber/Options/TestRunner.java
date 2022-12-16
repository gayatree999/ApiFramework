package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(publish = true,features="src/test/java/features",glue={"stepDefinations"})
public class TestRunner {

}
