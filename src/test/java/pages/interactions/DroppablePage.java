package pages.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppablePage {

    private final WebDriver driver;
    private Actions action;

    @FindBy(id = "draggable")
    private WebElement draggableElement;

    @FindBy(id = "droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    public void dragElement(WebElement element){
        action.moveToElement(element).clickAndHold();
    }

    public void dropElement(WebElement element){
        action.moveToElement(element).release().perform();
    }

    public WebElement getDraggableElement() {
        return draggableElement;
    }

    public WebElement getDroppableElement() {
        return droppableElement;
    }

}
