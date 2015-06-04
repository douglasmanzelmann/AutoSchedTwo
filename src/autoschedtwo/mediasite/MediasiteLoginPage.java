package autoschedtwo.mediasite;

import autoschedtwo.mediasite.Mediasite;
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
public class MediasiteLoginPage extends Mediasite {
    @FindBy(how = How.ID, using = "UserName")
    WebElement username;

    @FindBy(how = How.ID, using = "Password")
    WebElement password;

    @FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/form/div/fieldset/p/input")
    WebElement submit;

    public MediasiteLoginPage(WebDriver driver) {
        super(driver);
    }

    public MediasiteHomePage login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        submit.click();

        return new MediasiteHomePage(driver);

    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        //MediasiteLoginPage page = new MediasiteLoginPage(driver);
        MediasiteLoginPage loginPage =  PageFactory.initElements(driver, MediasiteLoginPage.class);

        Scanner input = new Scanner(System.in);
        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        loginPage.login(username, password);
    }

}
