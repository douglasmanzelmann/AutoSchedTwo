package autoschedtwo.listing;

import autoschedtwo.DateUtils;
import autoschedtwo.Scheduler;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by dmanzelmann on 5/26/2015.
 */
public abstract class Listing implements Scheduler {
    //private final int ID;
    private DateTime startTime;
    private DateTime endTime;
    private List<String> locations;
    private String className;
    private String classPrefix;
    private List<String> faculty;

    public Listing(PortalScheduleEventsEvent event) {
        //ID = event.getId();
        setStartTime(DateUtils.getDateTimeObject(event.getDate(), event.getStartTime()));
        setEndTime(DateUtils.getDateTimeObject(event.getDate(), event.getEndTime()));
        setLocations(event.getLocations());
        setClassName(event.getClassDetails().split(";")[0]);
        setClassPrefix(className.split(" ")[0]);
        setFaculty(event.getFaculty());
    }

   /* public int getID() {
        return ID;
    }*/

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

    protected static int getCurrentYear() {
        return new DateTime().getYear();
    }

    protected static int convertMonthAbbreviationToInt(String monthAbbreviation) {
        switch(monthAbbreviation) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
        }

        return -1;
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

    public List<String> getLocations() {
        return new ArrayList<>(locations);
    }

    public void setLocations(List<String> locations) {
        this.locations = new ArrayList<>(locations);
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

    public String toString() {
        return className + " " + StringUtils.join(locations, ",") + " " + startTime + " " + endTime;
    }

    public static void main(String[] args) {
        String time = "10:00 AM � 10:50 AM";
        System.out.println(time.indexOf('�'));
    }
}
