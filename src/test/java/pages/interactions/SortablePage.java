package pages.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SortablePage {

    private final WebDriver driver;
    private Actions actions;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='One']")
    private WebElement element1;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='Two']")
    private WebElement element2;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='Three']")
    private WebElement element3;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='Four']")
    private WebElement element4;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='Five']")
    private WebElement element5;

    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[@class='list-group-item list-group-item-action'][normalize-space()='Six']")
    private WebElement element6;

    @FindBy(css = "div[class='element-list collapse show'] li[id='item-3'] span[class='text']")
    private WebElement droppableButton;

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public void switchElements(WebElement source, WebElement target) {
        int xOffset = target.getLocation().getX() - source.getLocation().getX();
        int yOffset = target.getLocation().getY() - source.getLocation().getY();

        actions.clickAndHold(source)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    public WebElement getElement(Integer i){
        return switch (i) {
            case -1 -> droppableButton;
            case 1 -> element1;
            case 2 -> element2;
            case 3 -> element3;
            case 4 -> element4;
            case 5 -> element5;
            case 6 -> element6;
            default -> null;
        };
    }

    public List<WebElement> getElementsList() throws InterruptedException {
        Thread.sleep(300);
        return driver.findElements(By.xpath("//*[@id=\"demo-tabpane-list\"]/div/div"));
    }

    public String getTextForElementNumber(Integer number){
        return switch (number){
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            case 6 -> "Six";
            default -> null;
        };
    }

}
