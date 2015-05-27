package autoschedtwo.listing;

import autoschedtwo.Scheduler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public abstract class Listing implements Scheduler {
    private DateTime startTime;
    private DateTime endTime;
    private String location;
    private String className;
    private String classPrefix;
    private List<String> faculty;

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    protected static String getHour(DateTime date) {
        return Integer.toString(date.getHourOfDay());
    }

    protected static String getMinute(DateTime date) {
        return Integer.toString(date.getMinuteOfHour());
    }

    protected static String getTimeOfDay(DateTime date) {
        DateTimeFormatter timeOfDay = DateTimeFormat.forPattern("a");
        return date.toString(timeOfDay);
    }

    /**
     * Return the date as a String in MM/DD/YYYY format
     * @param date a DateTime object
     * @return a String
     */
    protected static String getDateInMDYFormat(DateTime date) {
        DateTimeFormatter MDYFmt = DateTimeFormat.forPattern("MM/dd/y");
        return date.toString(MDYFmt);
    }


    /**
     * Return the time in "h:mm a" format
     * @param date a DateTime object
     * @return a String
     */
    protected static String getTime(DateTime date) {
        DateTimeFormatter time = DateTimeFormat.forPattern("h:mm a");
        return date.toString(time);
    }

    protected static String durationInMinutes(DateTime one, DateTime two) {
        org.joda.time.Duration duration = new org.joda.time.Duration(one, two);
        return Long.toString(duration.getStandardMinutes());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassPrefix() {
        return classPrefix;
    }

    public void setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
    }

    public List<String> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<String> faculty) {
        this.faculty = faculty;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Listing(List<WebElement> listing) {
        setStartTime(startTime);
        setEndTime(endTime);
        setLocation(location);
        setClassName(className);
        setClassPrefix(className);
        setFaculty(faculty);
    }
}
