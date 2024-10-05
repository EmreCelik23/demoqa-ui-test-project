package pages.forms;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.io.File;
import java.util.Objects;

public class PracticeFormPage {

    private WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement fnameBox;

    @FindBy(id = "lastName")
    private WebElement lnameBox;

    @FindBy(id = "userEmail")
    private WebElement emailBox;

    @FindBy(id = "userNumber")
    private WebElement phoneNumberBox;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateBox;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsBox;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureButton;

    @FindBy(id = "currentAddress")
    private WebElement curAddressBox;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "react-select-3-input")
    private WebElement stateSelectionBox;

    @FindBy(id = "react-select-4-input")
    private WebElement citySelectionBox;


    public PracticeFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFullName(String fname, String lname){
        fnameBox.sendKeys(fname);
        lnameBox.sendKeys(lname);
    }

    public void enterEmail(String email){
        emailBox.sendKeys(email);
    }

    public void selectGender(String gender){
        switch (gender) {
            case "Male" -> driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
            case "Female" -> driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click();
            case "Other" -> driver.findElement(By.cssSelector("label[for='gender-radio-3']")).click();
            default -> throw new IllegalStateException("Given gender is not an option");
        }
    }

    public void enterPhoneNumber(String phone){
        if(phone.length() != 10){
            throw new IllegalStateException("Length of Phone Number must be 10");
        }
        else{
            phoneNumberBox.sendKeys(phone);
        }
    }

    public void enterDate(String date){
        if (date.matches("[0-9]{2} [A-Za-z]{3} [0-9]{4}")){
            dateBox.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", dateBox);
            dateBox.sendKeys(date, Keys.ENTER);
        }
        else {
            throw new IllegalStateException("Date must be in the format as 20 Mar 2003");
        }
    }

    public void enterSubject(String[] subjects){
        for (String subject : subjects){
            subjectsBox.sendKeys(subject);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            subjectsBox.sendKeys(Keys.ENTER);
        }
    }

    public void enterHobbies(String[] hobbies){
        for (String hobby : hobbies){
            switch (hobby){
                case "Sports" -> driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
                case "Reading" -> driver.findElement(By.cssSelector("label[for='hobbies-checkbox-2']")).click();
                case "Music" -> driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3']")).click();
            }
        }
    }


    public void selectPicture(String picturePath){
        File file = new File(picturePath);
        if (!file.exists()) {
            throw new IllegalStateException("File not found!");
        } else {
            uploadPictureButton.sendKeys(file.getAbsolutePath());
        }
    }

    public void enterCurrentAddress(String curAddress){
        curAddressBox.sendKeys(curAddress);
    }

    public void enterStateCity(String state, String city){
        stateSelectionBox.sendKeys(state, Keys.ENTER);
        citySelectionBox.sendKeys(city, Keys.ENTER);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public String getEmailBoxColor(){
        return emailBox.getCssValue("border-top-color");
    }

    public String getFnameBoxColor(){
        return fnameBox.getCssValue("border-top-color");
    }

    public String getGenderButtonColor(){
        return driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label")).getCssValue("color");
    }

    public void scrollUntilButtonVisible(WebElement button){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", button);
    }
}
