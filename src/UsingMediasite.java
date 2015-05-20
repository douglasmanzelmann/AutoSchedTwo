import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class UsingMediasite {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        MediasiteLoginPage loginPage = PageFactory.initElements(driver, MediasiteLoginPage.class);

        Scanner input = new Scanner(System.in);
        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        loginPage.login(username, password);

        MediasiteHomePage homePage = PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), MediasiteHomePage.class);
        homePage.navigateToSchoolOfPharmacy();
        homePage.navigateToPharmD();
    }
}
