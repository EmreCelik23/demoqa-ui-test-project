package stepDefinitions.alerts_frame_windows;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.alerts_frame_windows.NestedFramesPage;
import static org.junit.Assert.*;
import java.time.Duration;



public class NestedFramesTest {

    private WebDriver driver;
    private NestedFramesPage page;

    @Before("@nestedFrames")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new NestedFramesPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After("@nestedFrames")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Nested Frames page")
    public void the_user_is_on_the_nested_frames_page() {
        driver.get("https://demoqa.com/nestedframes");
    }

    @When("the user switches to the parent frame")
    public void the_user_switches_to_the_parent_frame() {
        page.switchToParentFrame();
    }

    @Then("the user should see the text Parent frame")
    public void the_user_should_see_the_text_parent_frame() {
        assertEquals(page.getTextInTheActiveFrame(), "Parent frame");
    }

    @When("the user switches to the child frame within the parent frame")
    public void the_user_switches_to_the_child_frame_within_the_parent_frame() {
        page.switchToChildFrame();
    }

    @Then("the user should see the text Child Iframe")
    public void the_user_should_see_the_text_child_iframe() {
        assertEquals(page.getTextInTheActiveFrame(), "Child Iframe");
    }
    
}
