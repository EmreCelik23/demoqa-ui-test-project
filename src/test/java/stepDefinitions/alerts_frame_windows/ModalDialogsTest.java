package stepDefinitions.alerts_frame_windows;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.alerts_frame_windows.ModalDialogsPage;
import static org.junit.Assert.*;

import java.time.Duration;



public class ModalDialogsTest {

    private WebDriver driver;
    private ModalDialogsPage page;

    @Before("@modals")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new ModalDialogsPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After("@modals")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Modal Dialogs page")
    public void the_user_is_on_the_modal_dialogs_page() {
        driver.get("https://demoqa.com/modal-dialogs");
    }
    @When("the user clicks the Small Modal button")
    public void the_user_clicks_the_small_modal_button() {
        page.clickSmallModalButton();
    }
    @Then("a small modal should appear with the message This is a small modal. It has very less content")
    public void a_small_modal_should_appear_with_the_message_this_is_a_small_modal_it_has_very_less_content() {
        assertEquals(page.getSmallModalContent(), "This is a small modal. It has very less content");
    }
    @When("the user clicks the Close button on the small modal")
    public void the_user_clicks_the_close_button_on_the_small_modal() {
        page.closeSmallModal();
    }

    @Then("the small modal should be closed")
    public void the_small_modal_should_be_closed() {
        boolean isSmallModalClosed = false;

        try {
            Thread.sleep(500);
            page.closeSmallModal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            isSmallModalClosed = true;
        }

        assertTrue(isSmallModalClosed);
    }

    @When("the user clicks the Large Modal button")
    public void the_user_clicks_the_large_modal_button() {
        page.clickLargeModalButton();
    }

    @Then("a large modal should appear with a message containing more text")
    public void a_large_modal_should_appear_with_a_message_containing_more_text() {
        String largeModalContent = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                " printer took a galley of type and scrambled it to make a type specimen book. It has survived" +
                " not only five centuries, but also the leap into electronic typesetting, remaining essentially"+
                " unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem"+
                " Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        assertEquals(page.getLargeModalContent(), largeModalContent);
    }

    @When("the user clicks the Close button on the large modal")
    public void the_user_clicks_the_close_button_on_the_large_modal() {
        page.closeLargeModal();
    }

    @Then("the large modal should be closed")
    public void the_large_modal_should_be_closed() {
        boolean isLargeModalClosed = false;

        try {
            Thread.sleep(500);
            page.closeLargeModal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            isLargeModalClosed = true;
        }

        assertTrue(isLargeModalClosed);
    }

    @When("the user clicks outside the modal dialog")
    public void the_user_clicks_outside_the_modal_dialog() {
        driver.findElement(By.xpath("//div[@role='dialog']")).click();
    }

}
