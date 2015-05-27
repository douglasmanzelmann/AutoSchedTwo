import autoschedtwo.outlook.OutlookCalendarPage;
import autoschedtwo.outlook.OutlookHomePage;
import autoschedtwo.outlook.OutlookLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class UsingOutlook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://umail.umaryland.edu");

        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        OutlookLoginPage loginPage = PageFactory.initElements(driver, OutlookLoginPage.class);
        OutlookHomePage homePage = loginPage.login(username, password);
        OutlookCalendarPage calendarPage = homePage.navigateToCalendar();
        calendarPage.scheduleNewAppointmentOnDay(28);

    }
}
