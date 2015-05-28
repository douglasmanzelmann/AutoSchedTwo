package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingFactory;
import autoschedtwo.portal.PortalDriver;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class AutoSched {
    WebDriver driver;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);

        System.out.print("Enter username (portal/mediasite): ");
        String username = input.next();

        System.out.print("Enter password(portal/mediasite): ");
        String password = input.next();

        System.out.print("Enter username (tms): ");
        String tmsUsername = input.next();

        System.out.print("Enter password (tms): ");
        String tmsPassword = input.next();

        System.out.print("Enter year: ");
        int year = Integer.parseInt(input.next());

        System.out.print("Enter month: ");
        int month = Integer.parseInt(input.next());

        System.out.print("Enter day: ");
        int day = Integer.parseInt(input.next());


        PortalDriver portalDriver = new PortalDriver(driver);
        List<PortalScheduleEventsEvent> events = portalDriver.getScheduleElements(username, password, year, month, day);
        for (PortalScheduleEventsEvent event : events) {
            Listing listing = ListingFactory.createListing(event);
            System.out.println(listing);
            //listing.schedule(username, password, tmsUsername, tmsPassword);
        }
    }
}
