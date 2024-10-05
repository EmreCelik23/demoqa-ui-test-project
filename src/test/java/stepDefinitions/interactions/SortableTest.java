package stepDefinitions.interactions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.*;
import pages.interactions.SortablePage;

import java.time.Duration;
import java.util.List;

public class SortableTest {

    private WebDriver driver;
    private SortablePage page;

    @Before("@sortable")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new SortablePage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@sortable")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Sortable page")
    public void the_user_is_on_the_sortable_page() {
        driver.get("https://demoqa.com/sortable");
    }

    @When("the user drags Item {int} to the position of Item {int}")
    public void the_user_drags_item_to_the_position_of_item(Integer int1, Integer int2) {
        page.switchElements(page.getElement(int1),page.getElement(int2));
    }

    @Then("the order of items should be Item {int}, Item {int}, Item {int}")
    public void the_order_of_items_should_be_item_item_item(Integer int1, Integer int2, Integer int3) throws InterruptedException {
        List<WebElement> elementLists = page.getElementsList();
        assertEquals(elementLists.get(0).getText(), page.getTextForElementNumber(int1));
        assertEquals(elementLists.get(1).getText(), page.getTextForElementNumber(int2));
        assertEquals(elementLists.get(2).getText(), page.getTextForElementNumber(int3));
    }

    @Then("the items should be displayed in their original order Item {int}, Item {int}, Item {int}")
    public void the_items_should_be_displayed_in_their_original_order_item_item_item(Integer int1, Integer int2, Integer int3) throws InterruptedException {
        List<WebElement> elementLists = page.getElementsList();
        assertEquals(elementLists.get(0).getText(), page.getTextForElementNumber(int1));
        assertEquals(elementLists.get(1).getText(), page.getTextForElementNumber(int2));
        assertEquals(elementLists.get(2).getText(), page.getTextForElementNumber(int3));
    }


    @Then("the grid layout should reflect the new order with Item {int} now above Item {int}")
    public void the_grid_layout_should_reflect_the_new_order_with_item_now_above_item(Integer int1, Integer int2) throws InterruptedException {
        List<WebElement> elementLists = page.getElementsList();
        assertTrue(elementLists.indexOf(page.getElement(int1)) < elementLists.indexOf(page.getElement(int2)));
    }

    @When("the user drags Item {int} to a new position and then drops it back")
    public void the_user_drags_item_to_a_new_position_and_then_drops_it_back(Integer int1) {
        page.switchElements(page.getElement(int1),page.getElement(4));
        page.switchElements(page.getElement(int1),page.getElement(4));
    }
    
}
