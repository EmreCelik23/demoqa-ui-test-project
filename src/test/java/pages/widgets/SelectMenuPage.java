package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectMenuPage {

    private final WebDriver driver;

    @FindBy(id = "withOptGroup")
    private WebElement singleSelectDropdown;

    @FindBy(id = "oldSelectMenu")
    private WebElement oldStyleSelectDropdown;

    @FindBy(xpath = "//div[@id='selectMenuContainer']//div[@class='row']//div[contains(@class,'css-1hwfws3')]")
    private WebElement multiSelectDropdown;

    @FindBy(id = "cars")
    private WebElement carsMultiSelectDropdown;

    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // WebElement'lerin başlatılması
    }

    public void clickSingleSelectDropdown() {
        singleSelectDropdown.click();
    }

    public void clickMultiSelectDropdown() {
        multiSelectDropdown.click();
    }

    public WebElement getSingleSelectDropdownOption(String option) {
        return switch (option) {
            case "Group 1, Option 1" -> driver.findElement(By.id("react-select-2-option-0-0"));
            case "Group 1, Option 2" -> driver.findElement(By.id("react-select-2-option-0-1"));
            case "Group 2, Option 1" -> driver.findElement(By.id("react-select-2-option-1-0"));
            case "Group 2, Option 2" -> driver.findElement(By.id("react-select-2-option-1-1"));
            case "A root option" -> driver.findElement(By.id("react-select-2-option-2"));
            case "Another root option" -> driver.findElement(By.id("react-select-2-option-3"));
            default -> null;
        };
    }

    public WebElement getMultiSelectDropdownOption(String option) {
        return switch (option) {
            case "Green" -> driver.findElement(By.id("react-select-4-option-0"));
            case "Blue" -> driver.findElement(By.id("react-select-4-option-1"));
            case "Black" -> driver.findElement(By.id("react-select-4-option-2"));
            case "Red" -> driver.findElement(By.id("react-select-4-option-3"));
            default -> null;
        };
    }

    public void selectSingleSelectDropdownOption(String option) throws InterruptedException {
        Thread.sleep(300);
        getSingleSelectDropdownOption(option).click();
    }

    public void selectMultiSelectDropdownOption(String option) {
        getMultiSelectDropdownOption(option).click();
    }

    // selectCarsMultiSelectDropdownOption method içinde Select nesnesini başlatıyoruz
    public void selectCarsMultiSelectDropdownOption(String option) {
        Select selectCarsDropdown = new Select(carsMultiSelectDropdown);
        selectCarsDropdown.selectByValue(option);
    }

    // selectOldStyleSelectDropdownOption method içinde Select nesnesini başlatıyoruz
    public void selectOldStyleSelectDropdownOption(String option) {
        Select selectOldStyleDropdown = new Select(oldStyleSelectDropdown);
        selectOldStyleDropdown.selectByVisibleText(option);
    }

    public boolean isCarsOptionSelected(String option) {
        Select selectCarsDropdown = new Select(carsMultiSelectDropdown);
        return selectCarsDropdown.getFirstSelectedOption().getText().equals(option);
    }

    public boolean isOldStyleOptionSelected(String option) {
        Select selectOldStyleDropdown = new Select(oldStyleSelectDropdown);
        return selectOldStyleDropdown.getFirstSelectedOption().getText().equals(option);
    }

}
