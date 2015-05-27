import autoschedtwo.outlook.OutlookCalendarPage;
import autoschedtwo.outlook.OutlookHomePage;
import autoschedtwo.outlook.OutlookLoginPage;
import autoschedtwo.outlook.OutlookNewAppointmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class UsingOutlook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://umail.umaryland.edu");

        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        OutlookLoginPage loginPage = PageFactory.initElements(driver, OutlookLoginPage.class);
        OutlookHomePage homePage = loginPage.login(username, password);
        OutlookCalendarPage calendarPage = homePage.navigateToCalendar();
        OutlookNewAppointmentPage newAppointmentPage = calendarPage.scheduleNewAppointmentOnDay(28);

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String mainWindow = iterator.next();
        String appointmentWindow = iterator.next();
        driver.switchTo().window(appointmentWindow);

        newAppointmentPage.schedule("Test subject", "N103", "12:00 PM", "1:00PM", "Mediasite");

    }
}
