package stepDefinitions.widgets;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.widgets.SliderPage;
import static org.junit.Assert.*;
import java.time.Duration;



public class SliderTest {

    private WebDriver driver;
    private SliderPage page;

    @Before("@slider")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new SliderPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@slider")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Slider page")
    public void the_user_is_on_the_slider_page() {
        driver.get("https://demoqa.com/slider");
    }

    @Then("the slider should be set to the default value {int}")
    public void the_slider_should_be_set_to_the_default_value(Integer int1) {
        assertEquals(page.getSliderStatus(), int1.toString());
    }

    @When("the user moves the slider to the value {int}")
    public void the_user_moves_the_slider_to_the_value(Integer int1) {
        page.moveSliderToGivenPercentage(int1);
    }

    @Then("the slider value should be displayed as {int}")
    public void the_slider_value_should_be_displayed_as(Integer int1) throws InterruptedException {
        Thread.sleep(400);
        assertEquals(page.getSliderStatus(), int1.toString());
    }

}
