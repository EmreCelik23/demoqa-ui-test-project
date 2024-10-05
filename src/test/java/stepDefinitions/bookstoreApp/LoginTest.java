package stepDefinitions.bookstoreApp;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.bookstoreApp.LoginPage;

import java.time.Duration;
import static org.junit.Assert.*;

public class LoginTest {

    private WebDriver driver;
    private LoginPage page;

    @Before("@login")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        page = new LoginPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@login")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://demoqa.com/login");
    }

    @When("the user enters a valid username")
    public void the_user_enters_a_valid_username() {
        String username = page.getValueFromCSVFile("username");
        page.enterUsername(username);
    }

    @When("the user enters a valid password")
    public void the_user_enters_a_valid_password() {
        String password = page.getValueFromCSVFile("password");
        page.enterPassword(password);
    }

    @When("the user clicks the Login button")
    public void the_user_clicks_the_login_button() {
        page.clickLoginButton();
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() throws InterruptedException {
        Thread.sleep(1000);
        assertEquals("https://demoqa.com/profile", driver.getCurrentUrl());
    }

    @When("the user enters an invalid username")
    public void the_user_enters_an_invalid_username() {
        page.enterUsername("invalidUsername");
    }

    @When("the user enters an invalid password")
    public void the_user_enters_an_invalid_password() {
        page.enterPassword("invalidPassword");
    }

    @Then("an error message should be displayed indicating Invalid credentials")
    public void an_error_message_should_be_displayed_indicating_invalid_credentials() {
        assertEquals("Invalid username or password!", driver.findElement(By.id("name")).getText());
    }

    @When("the user clicks the Login button without entering credentials")
    public void the_user_clicks_the_login_button_without_entering_credentials() {
        page.clickLoginButton();
    }

    @Then("an error message should be displayed indicating Username is required and Password is required")
    public void an_error_message_should_be_displayed_indicating_username_is_required_and_password_is_required() {
        assertEquals("mr-sm-2 is-invalid form-control", page.getUsernameBox().getAttribute("class"));
        assertEquals("mr-sm-2 is-invalid form-control", page.getPasswordBox().getAttribute("class"));
    }
    
}
