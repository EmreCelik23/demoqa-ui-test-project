package pages.alerts_frame_windows;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsPage {

    private final WebDriver driver;

    @FindBy(id = "alertButton")
    private WebElement simpleClickButton;

    @FindBy(id = "timerAlertButton")
    private WebElement timeClickButton;

    @FindBy(id = "confirmButton")
    private WebElement confirmClickButton;

    @FindBy(id = "promtButton")
    private WebElement promptClickButton;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSimpleButton() {
        simpleClickButton.click();
    }

    public void clickTimeButton() {
        timeClickButton.click();
    }

    public void clickConfirmButton() {
        confirmClickButton.click();
    }

    public void clickPromptButton() {
        promptClickButton.click();
    }

    public void scrollUntilButtonIsVisible(WebElement button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", button);
    }

    public WebElement getPromptClickButton() {
        return promptClickButton;
    }
}
