package stepDefinitions.widgets;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.widgets.ToolTipsPage;
import static org.junit.Assert.*;

import java.time.Duration;

public class ToolTipsTest {

    private WebDriver driver;
    private ToolTipsPage page;

    @Before("@tooltips")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();

        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        page = new ToolTipsPage(driver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After("@tooltips")
    public void quitBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the Tool Tips page")
    public void the_user_is_on_the_tool_tips_page() {
        driver.get("https://demoqa.com/tool-tips");
    }

    @When("the user hovers over the Hover me to see button")
    public void the_user_hovers_over_the_hover_me_to_see_button() {
        page.moveCursorToElement(page.getHoverButton());
    }

    @When("the user hovers over the Hover me to see text field")
    public void the_user_hovers_over_the_hover_me_to_see_text_field() {
        page.moveCursorToElement(page.getHoverBox());
    }

    @Then("a tooltip should appear with the text {string}")
    public void a_tooltip_should_appear_with_the_text_you_hovered_over_the_text_field(String text) {
        assertEquals(text, page.getToolTipText());
    }

    @When("the user hovers over the Hover me to see link")
    public void the_user_hovers_over_the_hover_me_to_see_link() {
        page.moveCursorToElement(page.getHoverLink());
    }

    @When("the user moves the mouse away from the button")
    public void the_user_moves_the_mouse_away_from_the_button() {
        
    }

    @Then("the tooltip should disappear")
    public void the_tooltip_should_disappear() {
        
    }

}
