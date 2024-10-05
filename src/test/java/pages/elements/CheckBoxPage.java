package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class CheckBoxPage {
    WebDriver driver;

    @FindBy(css = ".rct-icon.rct-icon-expand-close")
    private WebElement homeExpandButton;

    @FindBy(css = "label[for='tree-node-desktop'] span[class='rct-checkbox'] svg")
    private WebElement desktopCheckbox;

    @FindBy(css = "label[for='tree-node-downloads'] span[class='rct-checkbox'] svg")
    private WebElement downloadsCheckbox;

    public CheckBoxPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHomeExpandButton(){
        homeExpandButton.click();
    }

    public void selectDesktopBox(){
        desktopCheckbox.click();
    }

    public void selectDownloadsBox(){
        downloadsCheckbox.click();
    }

    public void deselectDownloadsBox(){
        if (Objects.equals(downloadsCheckbox.getAttribute("class"), "rct-icon rct-icon-check")){
            downloadsCheckbox.click();
        }
        else {
           throw new IllegalStateException("Downloads must be selected before deselect it");
        }
    }

    public boolean isDownloadsBoxSelected(){
        return Objects.equals(downloadsCheckbox.getAttribute("class"), "rct-icon rct-icon-check");
    }

    public boolean isDesktopBoxSelected(){
        return Objects.equals(desktopCheckbox.getAttribute("class"), "rct-icon rct-icon-check");
    }

    public boolean isTextEqual(List<String> expectedValues){
        List<String> actualValues = new ArrayList<>();
        for(WebElement element : driver.findElement(By.id("result")).findElements(By.cssSelector(".text-success"))){
            actualValues.add(element.getText());
        }

        Set<String> set1 = new HashSet<>(expectedValues);
        Set<String> set2 = new HashSet<>(actualValues);
        return set1.equals(set2);
    }

}
