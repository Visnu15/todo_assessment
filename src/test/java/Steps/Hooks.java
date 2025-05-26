package Steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Steps.BaseClass.driver;

public class Hooks extends BaseClass{

    public static Scenario scenario;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--start-maximized", "--disable-notifications");
        driver = new ChromeDriver(op);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    @Before
    public void beforeScenario(Scenario sc){
        scenario = sc;
    }

//    @BeforeAll
//    public static void launchURL(){
//        driver.get("https://todomvc.com/examples/react/dist/");
//    }

    @AfterStep
    public static void afterStep(){
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] ss = ts.getScreenshotAs(OutputType.BYTES);
//        scenario.embed(ss, "image/png");
        scenario.attach(ss, "image/png", "Screenshot taken!");
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    public static void log(String msg){
        scenario.log(msg);
    }

}
