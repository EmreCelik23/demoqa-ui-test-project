package pages.alerts_frame_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalDialogsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "showSmallModal")
    private WebElement smallModalButton;

    @FindBy(id = "showLargeModal")
    private WebElement largeModalButton;

    @FindBy(id = "closeSmallModal")
    private WebElement closeSmallModalButton;

    @FindBy(id = "closeLargeModal")
    private WebElement closeLargeModalButton;

    public ModalDialogsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSmallModalButton() {
        smallModalButton.click();
    }

    public void clickLargeModalButton() {
        largeModalButton.click();
    }

    public void closeSmallModal() {
        closeSmallModalButton.click();
    }

    public void closeLargeModal() {
        closeLargeModalButton.click();
    }

    public String getSmallModalContent(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='modal-body']"))));
        return driver.findElement(By.xpath("//div[@class='modal-body']")).getText();
    }

    public String getLargeModalContent(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='modal-body'] p"))));
        return driver.findElement(By.cssSelector("div[class='modal-body'] p")).getText();
    }
}
