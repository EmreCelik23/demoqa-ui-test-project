package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToolTipsPage {

    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;

    @FindBy(id = "toolTipButton")
    private WebElement hoverButton;

    @FindBy(id = "toolTipTextField")
    private WebElement hoverBox;

    @FindBy(css = "div[class='col-12 mt-4 col-md-6'] a:nth-child(1)")
    private WebElement hoverLink;

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void moveCursorToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        actions.moveToElement(element).build().perform();
    }

    public String getToolTipText() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='tooltip-inner']"))));
        return driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText();
    }

    public WebElement getHoverButton() {
        return hoverButton;
    }

    public WebElement getHoverBox() {
        return hoverBox;
    }

    public WebElement getHoverLink() {
        return hoverLink;
    }

}
