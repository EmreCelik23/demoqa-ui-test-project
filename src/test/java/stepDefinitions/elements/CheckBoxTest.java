package stepDefinitions.elements;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.elements.CheckBoxPage;
import static org.junit.Assert.*;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CheckBoxTest {

    private WebDriver driver;
    private CheckBoxPage page;

    @Before("@checkbox")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new CheckBoxPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@checkbox")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Checkbox page")
    public void the_user_is_on_the_checkbox_page() {
        driver.get("https://demoqa.com/checkbox");
    }

    @When("the user expands the Home checkbox")
    public void the_user_expands_the_home_checkbox() {
        page.clickHomeExpandButton();
    }

    @When("the user selects the Desktop checkbox")
    public void the_user_selects_the_desktop_checkbox() {
        page.selectDesktopBox();
    }

    @Then("the Desktop checkbox should be checked")
    public void the_desktop_checkbox_should_be_checked() {
        assertTrue(page.isDesktopBoxSelected());
    }

    @Then("the result should display You have selected : Desktop")
    public void the_result_should_display_you_have_selected_desktop() {
        List<String> expectedValues = Arrays.asList("desktop", "notes", "commands");
        assertTrue(page.isTextEqual(expectedValues));
    }

    @When("the user selects the Downloads checkbox")
    public void the_user_selects_the_downloads_checkbox() throws InterruptedException {
        Thread.sleep(300);
        page.selectDownloadsBox();
    }

    @When("the user unselects the Downloads checkbox")
    public void the_user_unselects_the_downloads_checkbox() {
        if(page.isDownloadsBoxSelected()){
            page.deselectDownloadsBox();
        }
        else {
            throw new IllegalStateException();
        }
    }

    @Then("the Downloads checkbox should be unchecked")
    public void the_desktop_checkbox_should_be_unchecked() {
        assertFalse(page.isDownloadsBoxSelected());
    }
    
}
