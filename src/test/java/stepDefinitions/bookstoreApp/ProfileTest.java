package stepDefinitions.bookstoreApp;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.bookstoreApp.LoginPage;
import pages.bookstoreApp.ProfilePage;
import static org.junit.Assert.*;
import java.time.Duration;

public class ProfileTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ProfilePage page;

    @Before("@profile")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        page = new ProfilePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@profile")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Profile page")
    public void the_user_is_on_the_profile_page() throws InterruptedException {
        driver.get("https://demoqa.com/login");
        LoginPage loginProperties = new LoginPage(driver);
        loginProperties.enterUsername(loginProperties.getValueFromCSVFile("username"));
        loginProperties.enterPassword(loginProperties.getValueFromCSVFile("password"));
        loginProperties.clickLoginButton();
        Thread.sleep(1000);
        assertEquals("https://demoqa.com/profile", driver.getCurrentUrl());
    }

    @When("the user clicks the Go To Book Store Button")
    public void the_user_clicks_the_go_to_book_store_button() {
        wait.until(ExpectedConditions.elementToBeClickable(page.getGoToBookstoreButton()));
        page.clickGoToBookstoreButton();
    }

    @Then("the user should be directed to Book Store Page")
    public void the_user_should_be_directed_to_book_store_page() {
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/books"));
        assertEquals("https://demoqa.com/books", driver.getCurrentUrl());
    }

    @When("the user clicks the Delete All Books Button")
    public void the_user_clicks_the_delete_all_books_button() {
        page.clickDeleteAllBooksButton();
    }

    @Then("a modal should appear with a message {string}")
    public void a_modal_should_appear_with_a_message(String string) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body"))));
        assertEquals(string, driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body")).getText());
    }

    @When("the user clicks the OK Button")
    public void the_user_clicks_the_ok_button() {
        driver.findElement(By.id("closeSmallModal-ok")).click();
    }

    @Then("an alert should appear with a message {string}")
    public void an_alert_should_appear_with_a_message(String string) {
        Alert alert = driver.switchTo().alert();
        assertEquals(string, alert.getText());
        alert.accept();
    }

    @When("the user clicks the Logout button")
    public void the_user_clicks_the_logout_button() {
        page.clickLogoutButton();
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/login"));
        assertEquals("https://demoqa.com/login", driver.getCurrentUrl());
    }

}
