package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingCreator;
import autoschedtwo.portal.PortalDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class AutoSched {
    WebDriver driver;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        System.out.print("Enter username: ");
        String username = input.next();

        System.out.print("Enter password: ");
        String password = input.next();

        System.out.print("Enter year: ");
        int year = Integer.parseInt(input.next());

        System.out.print("Enter month: ");
        int month = Integer.parseInt(input.next());

        System.out.print("Enter day: ");
        int day = Integer.parseInt(input.next());


        PortalDriver portalDriver = new PortalDriver(driver);
        ArrayList<ArrayList<WebElement>> scheduleElements = portalDriver.getScheduleElements(username, password, year, month, day);
        for (ArrayList<WebElement> list : scheduleElements) {
            Listing listing = ListingCreator.createListing(list);
            //listing.schedule();
        }
    }
}
