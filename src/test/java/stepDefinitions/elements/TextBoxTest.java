package stepDefinitions.elements;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.elements.TextBoxPage;
import static org.junit.Assert.*;
import java.time.Duration;

public class TextBoxTest {

    WebDriver driver;
    TextBoxPage page;
    String fullName, email, curAddress, perAddress;

    @Before("@textbox")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new TextBoxPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@textbox")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Text Box page")
    public void the_user_is_on_the_text_box_page() {
        driver.get("https://demoqa.com/text-box");
    }

    @When("^the user enters (.*) in the Full Name field$")
    public void the_user_enters_john_doe_in_the_full_name_field(String fullName) {
        this.fullName = fullName;
        page.enterUsername(fullName);
    }

    @When("^the user enters (.*) in the Email field$")
    public void the_user_enters_john_doe_example_com_in_the_email_field(String email) {
        this.email = email;
        page.enterEmail(email);
    }

    @When("^the user enters (.*) in the Current Address field$")
    public void the_user_enters_main_street_in_the_current_address_field(String curAddress) {
        this.curAddress = curAddress;
        page.enterCurrentAddress(curAddress);
    }
    @When("^the user enters (.*) in the Permanent Address field$")
    public void the_user_enters_elm_street_in_the_permanent_address_field(String perAddress) {
        this.perAddress = perAddress;
        page.enterPermanentAddress(perAddress);
    }
    @When("the user clicks the Submit button")
    public void the_user_clicks_the_submit_button() {
        page.scrollUntilButtonVisible();
        page.clickSubmitButton();
    }
    @Then("the form should display the entered details below the form")
    public void the_form_should_display_the_entered_details_below_the_form() {
        assertTrue("FullName Check", driver.findElement(By.xpath("//p[@id='name']")).getText().contains(fullName));
        assertTrue("Email Check", driver.findElement(By.xpath("//p[@id='email']")).getText().contains(email));
        assertTrue("Current Address Check", driver.findElement(By.xpath("//p[@id='currentAddress']")).getText().contains(curAddress));
        assertTrue("Permanent Address Check", driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText().contains(perAddress));
    }


    @And("the user enters invalid-email in the E-mail field")
    public void theUserEntersInvalidEmailInTheEmailField() {
        page.enterEmail("invalid-email");
    }

    @Then("an error message should be displayed for the Email field")
    public void anErrorMessageShouldBeDisplayedForTheEmailField() {
        assertTrue(driver.findElement(By.cssSelector(".mr-sm-2.field-error.form-control")).isDisplayed());
    }
}
