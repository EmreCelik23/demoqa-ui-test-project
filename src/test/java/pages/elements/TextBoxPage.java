package pages.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class TextBoxPage {
    WebDriver driver;

    @FindBy(id = "userName")
    private WebElement fullnameBox;

    @FindBy(id = "userEmail")
    private WebElement emailBox;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressBox;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public TextBoxPage(WebDriver driver){
        System.out.println(driver.getCurrentUrl());
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String str){
        fullnameBox.sendKeys(str);
    }

    public void enterEmail(String str){
        emailBox.sendKeys(str);
    }

    public void enterCurrentAddress(String str){
        currentAddressBox.sendKeys(str);
    }

    public void enterPermanentAddress(String str){
        permanentAddressBox.sendKeys(str);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public WebElement getEmailBox(){
        return emailBox;
    }

    public void scrollUntilButtonVisible(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
    }

}
