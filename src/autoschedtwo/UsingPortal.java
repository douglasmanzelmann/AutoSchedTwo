package autoschedtwo;

import autoschedtwo.portal.PortalLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class UsingPortal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://pharmacy.umaryland.edu/schedules/");
        PortalLoginPage loginPage = PageFactory.initElements(driver, PortalLoginPage.class);
        System.out.println("username: ");
        String username = input.next();
        System.out.println("password: ");
        String password = input.next();

        loginPage.login(username, password);
    }
}
