package stepDefinitions.widgets;

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
import pages.widgets.SelectMenuPage;
import static org.junit.Assert.*;
import java.time.Duration;



public class SelectMenuTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SelectMenuPage page;

    @Before("@selectMenu")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new SelectMenuPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@selectMenu")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Select Menu page")
    public void the_user_is_on_the_select_menu_page() {
        driver.get("https://demoqa.com/select-menu");
    }

    @When("the user selects Group {int}, Option {int} from the Select Value dropdown")
    public void the_user_selects_group_option_from_the_select_value_dropdown(Integer int1, Integer int2) throws InterruptedException {
        page.clickSingleSelectDropdown();
        page.selectSingleSelectDropdownOption("Group " + int1.toString() + ", Option " + int2.toString());
    }

    @Then("the selected value should be Group {int}, Option {int}")
    public void the_selected_value_should_be_group_option(Integer int1, Integer int2) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"withOptGroup\"]/div/div[1]/div[1]"))));
        String selectedOption = driver.findElement(By.xpath("//*[@id=\"withOptGroup\"]/div/div[1]/div[1]")).getText();
        assertEquals(selectedOption, "Group " + int1.toString() + ", option " + int2.toString());
    }

    @When("the user selects Green from the Old Style Select Menu")
    public void the_user_selects_green_from_the_old_style_select_menu() {
        page.selectOldStyleSelectDropdownOption("Green");
    }

    @Then("the selected option should be Green")
    public void the_selected_option_should_be_green() {
        assertTrue(page.isOldStyleOptionSelected("Green"));
    }

    @When("the user selects Red and Blue from the Multi Select Dropdown")
    public void the_user_selects_red_and_blue_from_the_multi_select_dropdown() {
        page.clickMultiSelectDropdown();
        page.selectMultiSelectDropdownOption("Red");
        page.selectMultiSelectDropdownOption("Blue");
    }

    @Then("the selected options should include Red and Blue")
    public void the_selected_options_should_include_red_and_blue() {
        assertEquals("Red", driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div/div/div[1]/div[1]/div/div[1]")).getText());
        assertEquals("Blue",driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div/div/div[1]/div[2]/div/div[1]")).getText());
    }

    @When("the user selects Audi from the Standard Multi Select dropdown")
    public void the_user_selects_audi_from_the_standard_multi_select_dropdown() {
        page.selectCarsMultiSelectDropdownOption("audi");
    }

    @Then("the selected car should be Audi")
    public void the_selected_car_should_be_audi() {
        assertTrue(page.isCarsOptionSelected("Audi"));
    }

}
