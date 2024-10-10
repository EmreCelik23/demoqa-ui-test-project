package stepDefinitions.alerts_frame_windows;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.alerts_frame_windows.AlertsPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;


public class AlertsTest {

    private WebDriver driver;
    private AlertsPage page;
    private WebDriverWait wait;

    @Before("@alerts")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new AlertsPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@alerts")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Alerts page")
    public void the_user_is_on_the_alerts_page() {
        driver.get("https://demoqa.com/alerts");
    }
    @When("the user clicks the Click me button to trigger a simple alert")
    public void the_user_clicks_the_click_me_button_to_trigger_a_simple_alert() {
        page.clickSimpleButton();
    }
    @Then("an alert should appear with the message You clicked a button")
    public void an_alert_should_appear_with_the_message_you_clicked_a_button() {
        assertEquals(driver.switchTo().alert().getText(), "You clicked a button");
    }
    @Then("the user accepts the alert")
    public void the_user_accepts_the_alert() {
        driver.switchTo().alert().accept();
    }
    @When("the user clicks the Click me button to trigger a timed alert")
    public void the_user_clicks_the_click_me_button_to_trigger_a_timed_alert() {
        page.clickTimeButton();
    }
    @Then("after 5 seconds an alert should appear with the message This alert appeared after 5 seconds")
    public void after_seconds_an_alert_should_appear_with_the_message_this_alert_appeared_after_seconds() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals(alert.getText(), "This alert appeared after 5 seconds");
    }
    @When("the user clicks the Click me button to trigger a confirmation alert")
    public void the_user_clicks_the_click_me_button_to_trigger_a_confirmation_alert() {
        page.clickConfirmButton();
    }
    @Then("an alert should appear with the message Do you confirm action?")
    public void an_alert_should_appear_with_the_message_do_you_confirm_action() {
        assertEquals(driver.switchTo().alert().getText(), "Do you confirm action?");

    }
    @Then("the result text should display You selected Ok")
    public void the_result_text_should_display_you_selected_ok() {
        assertEquals(driver.findElement(By.id("confirmResult")).getText(), "You selected Ok");
    }
    @When("the user dismisses the alert")
    public void the_user_dismisses_the_alert() {
        driver.switchTo().alert().dismiss();
    }
    @Then("the result text should display You selected Cancel")
    public void the_result_text_should_display_you_selected_cancel() {
        assertEquals(driver.findElement(By.id("confirmResult")).getText(), "You selected Cancel");
    }
    @When("the user clicks the Click me button to trigger a prompt alert")
    public void the_user_clicks_the_click_me_button_to_trigger_a_prompt_alert() {
        page.scrollUntilButtonIsVisible(page.getPromptClickButton());
        page.clickPromptButton();
    }
    @Then("an alert should appear with a prompt message Please enter your name")
    public void an_alert_should_appear_with_a_prompt_message_please_enter_your_name() {
        assertEquals(driver.switchTo().alert().getText(), "Please enter your name");
    }
    @When("the user enters Emre Celik into the prompt")
    public void the_user_enters_emre_celik_into_the_prompt() {
        driver.switchTo().alert().sendKeys("Emre Celik");
    }
    @Then("the result text should display You entered Emre Celik")
    public void the_result_text_should_display_you_entered_emre_celik() {
        assertEquals(driver.findElement(By.id("promptResult")).getText(), "You entered Emre Celik");
    }
}
