package stepDefinitions.bookstoreApp;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.bookstoreApp.BookStorePage;
import pages.bookstoreApp.LoginPage;
import pages.bookstoreApp.ProfilePage;
import static org.junit.Assert.*;
import java.time.Duration;



public class BookStoreTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private BookStorePage page;

    @Before("@bookstore")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        page = new BookStorePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@bookstore")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Login page to enter profile page")
    public void the_user_is_on_the_login_page_to_enter_profile_page() {
        driver.get("https://demoqa.com/login");
    }

    @When("the user enters a valid username in usernameBox")
    public void the_user_enters_a_valid_username_in_usernameBox() {
        loginPage.enterUsername(loginPage.getValueFromCSVFile("username"));
    }

    @When("the user enters a valid password in passwordBox")
    public void the_user_enters_a_valid_password_in_passwordBox() {
        loginPage.enterPassword(loginPage.getValueFromCSVFile("password"));
    }

    @When("the user clicks Login button")
    public void the_user_clicks_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be directed to the Profile Page")
    public void the_user_should_be_directed_to_the_profile_page() {
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/profile"));
        assertEquals("https://demoqa.com/profile", driver.getCurrentUrl());
    }

    @When("the user clicks the Go To Book Store Button in Profile Page")
    public void the_user_clicks_the_go_to_book_store_button_in_profile_page() {
        page.scrollUntilElementVisible(profilePage.getGoToBookstoreButton());
        profilePage.clickGoToBookstoreButton();
    }

    @Then("the user must be directed to Book Store Page")
    public void the_user_must_be_directed_to_book_store_page() {
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/books"));
        assertEquals("https://demoqa.com/books", driver.getCurrentUrl());
    }

    @Then("the contents of the books must be equal with the CSV File")
    public void the_contents_of_the_books_must_be_equal_with_the_csv_file() {
        page.initializeBookList();
        assertTrue(page.checkIfBooksHaveCorrectContent());
    }

}
