import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/20/2015.
 */
public class UsingMediasite {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);

        driver.get("https://mediasite.umaryland.edu/Mediasite/Login?ReturnUrl=%2fmediasite%2fmanage");

        MediasiteLoginPage loginPage = PageFactory.initElements(driver, MediasiteLoginPage.class);

        Scanner input = new Scanner(System.in);
        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        MediasiteHomePage homePage = loginPage.login(username, password);
        PageFactory.initElements(factory, homePage);
        homePage.navigateToSchoolOfPharmacy();
        homePage.navigateToPharmD();
        homePage.navigateToFolder("2015 Spring");

        MediasiteFolderPage folderPage = homePage.navigateToFolder("SP15 PHAR525");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), folderPage);
        folderPage.addNewPresentation();

    }
}
