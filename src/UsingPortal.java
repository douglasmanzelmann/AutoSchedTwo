import autoschedtwo.portal.PortalLoginPage;
import autoschedtwo.portal.PortalScheduleEventsWeekPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class UsingPortal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //https://rxsecure.umaryland.edu/apps/schedules/view/?type=search&searchtype=resource&id=100&start=2015-05-24&scope=week
        System.out.print("Enter the year: ");
        int year = Integer.parseInt(input.next());

        System.out.print("Enter the month: ");
        int month = Integer.parseInt(input.next());

        System.out.print("Enter the day: ");
        int day = Integer.parseInt(input.next());


        driver.get("https://rxsecure.umaryland.edu/apps/schedules/view/?type=search&searchtype=resource&id=100&start=" +
                year + "-" + month + "-" + day + "&scope=week");
        PortalLoginPage loginPage = PageFactory.initElements(driver, PortalLoginPage.class);
        System.out.println("username: ");
        String username = input.next();
        System.out.println("password: ");
        String password = input.next();

        PortalScheduleEventsWeekPage scheduleEventsWeekPage = loginPage.login(username, password);
        Iterator<WebElement> listIterator = scheduleEventsWeekPage.iterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}
