package autoschedtwo.portal;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmanzelmann on 5/27/2015.
 */
public class PortalScheduleEventsEvent {
    private WebDriver driver;
    private List<WebElement> elements;
    private List<String> listingElements;
    private String date;
    private String startTime;
    private String endTime;
    private List<String> locations;
    private String classDetails;
    private String activity;
    private List<String> faculty;


    private void setDate(String date) {
        this.date = date.trim();
    }

    public String getDate() {
        return date;
    }

    private void setTimes(String time) {
        String[] times = time.split(" ");
        startTime = times[0] + " " + times[1];
        endTime = times[3] + " " + times[4];
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    private void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getLocations() {
        return locations;
    }

    private void setClassDetails(String classDetails) {
        this.classDetails = classDetails;
    }

    public String getClassDetails() {
        return classDetails;
    }

    private void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    private void setFaculty(List<String> faculty) {
        this.faculty = new ArrayList<>(faculty);

        for (int i = 0; i < this.faculty.size(); i++) {
            if (this.faculty.get(i).contains("PHARMD"))
                this.faculty.remove(i);
        }
    }

    public List<String> getFaculty() {
        return new ArrayList<>(faculty);
    }


    public PortalScheduleEventsEvent parse() {
        Iterator<WebElement> iterator = elements.iterator();

        setDate(iterator.next().getAttribute("title")); // i.e., Apr 19
        setTimes(iterator.next().getText()); // i.e., 10:00 AM - 10:50 AM
        setLocations(Arrays.asList(iterator.next().getText().split("\n")));
        setClassDetails(StringUtils.join(iterator.next().getText().trim().split("\n"), ";"));

        if (classDetails.contains("Recorded in Mediasite"))
            setActivity("Mediasite");
        else if (classDetails.contains("Videoconference"))
            setActivity("Videoconference");
        else if (classDetails.contains("Pre-record Session"))
            setActivity("PreRecord");
        else
            setActivity("Generic");

        setFaculty(Arrays.asList(iterator.next().getText().split("\n")));
        return this;
    }

    public PortalScheduleEventsEvent(WebDriver driver, List<WebElement> elements) {
        this.driver = driver;
        this.elements = elements;
        elements = new ArrayList<>();
    }

    public static void main(String[] args) {
        String test = "PHAR539 Medicinal Chemistry 2 \n" +
                "\n" +
                "CNS Stimulants \n" +
                "\n" +
                " Recorded in Mediasite";
        System.out.println(StringUtils.join(test.trim().split("\n"), ";"));

        String pattern = "(\\d\\d:\\d\\d [AP]M)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("10:00 AM — 10:50 AM");
        while (m.find()) {
            System.out.println("found value: " + m.group(0));
        }

        //String SPLIT = " — ";
        //String time = "10:00 AM — 10:50 AM";
        //System.out.println("contains split: " + time.contains(SPLIT));
    }
}
