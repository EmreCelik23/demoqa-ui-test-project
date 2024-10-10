package stepDefinitions.forms;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.forms.PracticeFormPage;
import static org.junit.Assert.*;

import java.time.Duration;

public class PracticeFormTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private PracticeFormPage page;
    private String fname, lname, email, gender, phone, picturePath, curAddress, state, city;
    private String[] date, hobbies, subjects;

    @Before("@practiceForm")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new PracticeFormPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        fname = "Emre";
        lname = "Celik";
        email = "emrecelik@gmail.com";
        gender = "Male";phone = "0555111333";
        date = new String[]{"20","March","2003"};
        picturePath = "src/test/resources/images/DameTime.png";
        curAddress = "Umraniye/Istanbul";
        state = "Haryana";
        city = "Panipat";
        hobbies = new String[]{"Sports", "Music"};
        subjects = new String[]{"Chemistry", "Computer Science"};
    }

    @After("@practiceForm")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Practice Form page")
    public void the_user_is_on_the_practice_form_page() {
        driver.get("https://demoqa.com/automation-practice-form");
    }
    @When("the user enters all the required fields")
    public void the_user_enters_all_the_required_fields() {
        page.enterFullName(fname, lname);
        page.enterEmail(email);
        page.scrollUntilButtonVisible(driver.findElement(By.cssSelector("label[for='gender-radio-1']")));
        page.selectGender(gender);
        page.enterPhoneNumber(phone);
        page.enterDate(date[0] + " " + date[1].substring(0,3) + " " + date[2]);
        page.enterSubject(subjects);
        page.enterHobbies(hobbies);
        page.selectPicture(picturePath);
        page.enterCurrentAddress(curAddress);
        page.enterStateCity(state, city);
    }
    @When("the user clicks the Submit button in the form")
    public void the_user_clicks_the_submit_button_in_the_form() {
        page.scrollUntilButtonVisible(driver.findElement(By.id("submit")));
        page.clickSubmitButton();
    }
    @Then("a confirmation dialog should appear with the submitted information")
    public void a_confirmation_dialog_should_appear_with_the_submitted_information() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(1) > td:nth-child(2)"))));

        String fullNameTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(1) > td:nth-child(2)")).getText();
        String emailTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText();
        String genderTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(3) > td:nth-child(2)")).getText();
        String mobileTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
        String dateTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
        String[] subjectsTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(6) > td:nth-child(2)")).getText().split(", ");
        String[] hobbiesTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(7) > td:nth-child(2)")).getText().split(", ");
        String pictureNameTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(8) > td:nth-child(2)")).getText();
        String addressTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(9) > td:nth-child(2)")).getText();
        String stateCityTable = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(10) > td:nth-child(2)")).getText();
        String[] parts = picturePath.split("/");
        String fileName = parts[parts.length - 1];

        assertEquals(fullNameTable, (fname + " " + lname));
        assertEquals(emailTable, email);
        assertEquals(genderTable, gender);
        assertEquals(mobileTable, phone);
        assertEquals(dateTable, (date[0] + " " + date[1] + "," + date[2]));
        assertArrayEquals(subjectsTable, subjects);
        assertArrayEquals(hobbiesTable, hobbies);
        assertEquals(pictureNameTable, fileName);
        assertEquals(addressTable, curAddress);
        assertEquals(stateCityTable, (state + " " + city));
    }

    @When("the user leaves the First Name field empty and enters all other required fields")
    public void the_user_leaves_the_first_name_field_empty_and_enters_all_other_required_fields() {
        driver.findElement(By.id("lastName")).sendKeys(lname);
        page.enterEmail(email);
        page.scrollUntilButtonVisible(driver.findElement(By.cssSelector("label[for='gender-radio-1']")));
        page.selectGender(gender);
        page.enterPhoneNumber(phone);
        page.enterDate(date[0] + " " + date[1].substring(0,3) + " " + date[2]);
        page.enterSubject(subjects);
        page.enterHobbies(hobbies);
        page.selectPicture(picturePath);
        page.enterCurrentAddress(curAddress);
        page.enterStateCity(state, city);
    }
    @Then("an error message should appear for the First Name field")
    public void an_error_message_should_appear_for_the_first_name_field() {
        assertTrue(isColorInRange(page.getFnameBoxColor(), 207,196, 203));

    }
    @When("the user enters all the required fields and invalid-email in the Email field in the form")
    public void the_user_enters_all_the_required_fields_and_invalid_email_in_the_email_field_in_the_form() {
        page.enterFullName(fname, lname);
        page.enterEmail("invalid-email");
        page.scrollUntilButtonVisible(driver.findElement(By.cssSelector("label[for='gender-radio-1']")));
        page.selectGender(gender);
        page.enterPhoneNumber(phone);
        page.enterDate(date[0] + " " + date[1].substring(0,3) + " " + date[2]);
        page.enterSubject(subjects);
        page.enterHobbies(hobbies);
        page.selectPicture(picturePath);
        page.enterCurrentAddress(curAddress);
        page.enterStateCity(state, city);
    }
    @Then("an error message should appear for the Email field")
    public void an_error_message_should_appear_for_the_email_field() {
        assertTrue(isColorInRange(page.getEmailBoxColor(), 207,196, 203));
    }
    @When("the user fills out all the required fields except the Gender")
    public void the_user_fills_out_all_the_required_fields_except_the_gender() {
        page.enterFullName(fname, lname);
        page.enterEmail(email);
        page.scrollUntilButtonVisible(driver.findElement(By.cssSelector("label[for='gender-radio-1']")));
        page.enterPhoneNumber(phone);
        page.enterDate(date[0] + " " + date[1].substring(0,3) + " " + date[2]);
        page.enterSubject(subjects);
        page.enterHobbies(hobbies);
        page.selectPicture(picturePath);
        page.enterCurrentAddress(curAddress);
        page.enterStateCity(state, city);
    }
    @Then("an error message should appear for the Gender field")
    public void an_error_message_should_appear_for_the_gender_field() {
        assertTrue(isColorInRange(page.getGenderButtonColor(), 220,53, 69));
    }

    public boolean isColorInRange(String value, int expectedR, int expectedG, int expectedB){
        String[] rgba =value.replace("rgba(", "").replace(")", "").split(", ");
        int r = Integer.parseInt(rgba[0]);
        int g = Integer.parseInt(rgba[1]);
        int b = Integer.parseInt(rgba[2]);

        int tolerance = 30;

        return (Math.abs(r - expectedR) <= tolerance) &&
                (Math.abs(g - expectedG) <= tolerance) &&
                (Math.abs(b - expectedB) <= tolerance);
    }
}

