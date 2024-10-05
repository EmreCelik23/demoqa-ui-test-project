package pages.bookstoreApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "userName")
    private WebElement usernameBox;

    @FindBy(id = "password")
    private WebElement passwordBox;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterUsername(String username) {
        usernameBox.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordBox.sendKeys(password);
    }

    public WebElement getUsernameBox() {
        return usernameBox;
    }

    public WebElement getPasswordBox() {
        return passwordBox;
    }

    public String getValueFromCSVFile(String type) {
        String csvFile = "src/test/resources/bookstore_user_infos/userInfos.csv";
        String[] values;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            values = br.readLine().split(";");

            return switch (type) {
                case "username" -> values[0];
                case "password" -> values[1];
                default -> null;
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
