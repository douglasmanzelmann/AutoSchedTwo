import autoschedtwo.tms.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class UsingTMS {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        TMSLoginPage loginPage = new TMSLoginPage(driver);

        System.out.println("username: ");
        String username = input.next();

        System.out.println("password: ");
        String password = input.next();

        TMSHomePage homePage = loginPage.login(username, password);

        try {

            TMSConferenceTemplatesPage conferenceTemplatesPage = homePage.navigateToConferenceTemplates();

            TMSNewConferencePage newConferencePage = conferenceTemplatesPage.selectTemplateforVTC("PH N103", "USG 2131");
            TMSNewConferenceReviewPage conferenceReviewPage =
                    newConferencePage.createNewTMSSlot("Manzelmann", "5/25/2015", "11:00 AM", "5/25/2015", "12:00 PM", "1:00");
        } catch (CodecInUseException e) {
            System.out.println("Codec is in use.");
        }

    }
}
