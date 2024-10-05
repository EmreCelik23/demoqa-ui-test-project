package pages.bookstoreApp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;

public class BookStorePage {

    private final WebDriver driver;
    private WebDriverWait wait;

    private List<WebElement> bookList;

    public BookStorePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void initializeBookList(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]"))));
        bookList = driver.findElements(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div"));
    }

    public boolean checkIfBooksHaveCorrectContent() {
        String csvFile = "src/test/resources/bookstore_user_infos/booksInfo.csv";
        String bookTitle, bookAuthor, bookPublisher;
        String[] values;
        String csvBookTitle, csvBookAuthor, csvBookPublisher;

        try{
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();

            bookList.remove(bookList.size()-1);
            bookList.remove(bookList.size()-1);

            for (WebElement element : bookList) {

                bookTitle = element.findElement(By.xpath(".//div/div[2]/div/span")).getText();
                bookAuthor = element.findElement(By.xpath(".//div/div[3]")).getText();
                bookPublisher = element.findElement(By.xpath(".//div/div[4]")).getText();

                values = br.readLine().split(";");

                csvBookTitle = values[0];
                csvBookAuthor = values[1];
                csvBookPublisher = values[2];

                if (!bookTitle.equals(csvBookTitle) || !bookAuthor.equals(csvBookAuthor) || !bookPublisher.equals(csvBookPublisher)) {
                    return false;
                }
            }
            br.close();

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void scrollUntilElementVisible(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
