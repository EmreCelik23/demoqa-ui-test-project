package pages.widgets;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ProgressBarPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "startStopButton")
    private WebElement startStopButton;

    @FindBy(id = "resetButton")
    private WebElement resetButton;

    @FindBy(xpath = "//div[@role='progressbar']")
    private WebElement progressBar;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void startProgress(){
        startStopButton.click();
    }

    public void resetProgress(){
        resetButton.click();
    }

    public void stopProgressAtGivenPercentage(Integer percentage){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(25))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(driver -> {
            String currentValue = progressBar.getAttribute("aria-valuenow");
            return Objects.equals(currentValue, percentage.toString());
        });

        startStopButton.click();
    }

    public String getProgressPercentage(){
        return progressBar.getAttribute("aria-valuenow");
    }

    public void waitUntilFinished(){
        wait.until(driver -> Objects.equals(progressBar.getAttribute("aria-valuenow"), "100"));
    }
}
