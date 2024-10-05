package stepDefinitions.interactions;

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
import pages.interactions.DroppablePage;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

public class DroppableTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private DroppablePage page;

    @Before("@droppable")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new DroppablePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@droppable")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Droppable page")
    public void the_user_is_on_the_droppable_page() {
        driver.get("https://demoqa.com/droppable");
    }

    @When("the user drags the Drag me box")
    public void the_user_drags_the_drag_me_box() {
        wait.until(ExpectedConditions.visibilityOf(page.getDraggableElement()));
        page.dragElement(page.getDraggableElement());
    }

    @When("the user drops it into the Drop here area")
    public void the_user_drops_it_into_the_drop_here_area() {
        wait.until(ExpectedConditions.visibilityOf(page.getDroppableElement()));
        page.dropElement(page.getDroppableElement());
    }

    @Then("the Drop here area should display Dropped!")
    public void the_drop_here_area_should_display_dropped() {
        wait.until(ExpectedConditions.textToBePresentInElement(page.getDroppableElement(), "Dropped!"));
        assertEquals(page.getDroppableElement().getText(), "Dropped!");
    }

    @Then("the Drop here area should initially display Drop here")
    public void the_drop_here_area_should_initially_display_drop_here() {
        assertEquals(page.getDroppableElement().getText(), "Drop here");
    }

    @When("the user drops it outside the Drop here area")
    public void the_user_drops_it_outside_the_drop_here_area() {
        page.dropElement(driver.findElement(By.xpath("//h1[@class='text-center']")));
    }

    @Then("the Drop here area should still display Drop here")
    public void the_drop_here_area_should_still_display_drop_here() {
        wait.until(ExpectedConditions.textToBePresentInElement(page.getDroppableElement(), "Drop here"));
        assertEquals(page.getDroppableElement().getText(), "Drop here");
    }
    
}
