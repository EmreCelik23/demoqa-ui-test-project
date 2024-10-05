package stepDefinitions.widgets;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.interactions.SortablePage;
import pages.widgets.ProgressBarPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgressBarTest {

    private WebDriver driver;
    private ProgressBarPage page;

    @Before("@progressBar")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new ProgressBarPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@progressBar")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Progress Bar page")
    public void the_user_is_on_the_progress_bar_page() {
        driver.get("https://demoqa.com/progress-bar");
    }

    @When("the user clicks the Start button")
    public void the_user_clicks_the_start_button() {
        page.startProgress();
    }

    @Then("the progress bar text should be {int}%")
    public void the_progress_bar_text_should_be(Integer int1) {
        assertEquals(page.getProgressPercentage(), int1.toString());
    }

    @When("the user clicks the Stop button when the progress bar reaches {int}%")
    public void the_user_clicks_the_stop_button_when_the_progress_bar_reaches(Integer int1) {
        page.stopProgressAtGivenPercentage(int1);
    }

    @When("the user clicks the Reset button")
    public void the_user_clicks_the_reset_button() {
        page.resetProgress();
    }

    @When("the user waits until the end")
    public void the_user_waits_until_the_end() {
        page.waitUntilFinished();
    }
    
}
