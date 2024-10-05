package pages.bookstoreApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    private final WebDriver driver;

    @FindBy(id = "gotoStore")
    private WebElement goToBookstoreButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div[3]/div[3]/button")
    private WebElement deleteAllBooksButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div[1]/div[3]/button")
    private WebElement logoutButton;

    public WebElement getGoToBookstoreButton() {
        return goToBookstoreButton;
    }

    public WebElement getDeleteAllBooksButton() {
        return deleteAllBooksButton;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void clickGoToBookstoreButton() {
        goToBookstoreButton.click();
    }

    public void clickDeleteAllBooksButton() {
        deleteAllBooksButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
