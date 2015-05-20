import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class MediasiteLoginPage {
    @FindBy(how = How.ID, using = "UserName")
    WebElement username;

    @FindBy(how = How.ID, using = "Password")
    WebElement password;

    @FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/form/div/fieldset/p/input")
    WebElement submit;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        submit.click();
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        MediasiteLoginPage page = PageFactory.initElements(driver, MediasiteLoginPage.class);

        Scanner input = new Scanner(System.in);
        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        page.login(username, password);
    }

}
