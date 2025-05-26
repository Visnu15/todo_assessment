package Runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)

@io.cucumber.junit.CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
        features = {"./Feature/ToDo.feature"},
        glue = "Steps",
        dryRun = !true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true

)


public class Runner {
}
