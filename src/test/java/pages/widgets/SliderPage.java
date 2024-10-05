package pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage {

    private final WebDriver driver;
    private final Actions actions;

    @FindBy(id = "sliderValue")
    private WebElement sliderStatus;

    @FindBy(xpath = "//input[@type='range']")
    private WebElement slider;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public Integer getSliderWidth() {
        return slider.getSize().getWidth();
    }

    public void moveSliderToGivenPercentage(int percentage) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage should be between 0 and 100");
        }

        int currentPercentage = Integer.parseInt(getSliderStatus());
        int percentageDifference = percentage - currentPercentage;
        int location = (int) (getSliderWidth() * (percentageDifference / 100.0));

        actions.clickAndHold(slider)
                .moveByOffset(location - getCurrentSliderPosition(), 0)
                .release()
                .perform();
    }

    public String getSliderStatus(){
        return sliderStatus.getAttribute("value");
    }

    private int getCurrentSliderPosition() {
        String value = getSliderStatus();
        int currentPercentage = Integer.parseInt(value);
        return (int) (getSliderWidth() * (currentPercentage / 100.0));
    }

}
