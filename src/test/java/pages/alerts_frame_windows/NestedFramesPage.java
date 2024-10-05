package pages.alerts_frame_windows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NestedFramesPage {

    private final WebDriver driver;

    @FindBy(id = "frame1")
    private WebElement parentFrame;

    @FindBy(css = "iframe[srcdoc='<p>Child Iframe</p>']")
    private WebElement childFrame;

    public NestedFramesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void switchToParentFrame () {
        driver.switchTo().frame(parentFrame);
    }

    public void switchToChildFrame () {
        driver.switchTo().frame(childFrame);
    }

    public String getTextInTheActiveFrame (){
        return driver.switchTo().activeElement().getText();
    }
}
