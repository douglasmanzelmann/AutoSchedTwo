package autoschedtwo.listing;

import autoschedtwo.DateUtils;
import autoschedtwo.Scheduler;
import autoschedtwo.GuiUtils.Screenshot;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by dmanzelmann on 5/26/2015.
 */
public abstract class Listing implements Scheduler, Screenshot {
    private final DateTime startTime;
    private final DateTime endTime;
    private final List<String> locations;
    private final String className;
    private final String classPrefix;
    private final List<String> faculty;
    private String activity;
    private boolean needsToBeScheduled;
    private boolean canBeScheduled;
    private boolean isScheduled;
    private String status;

    public Listing(PortalScheduleEventsEvent event) {
        this.startTime = DateUtils.getDateTimeObject(event.getDate(), event.getStartTime());
        //setStartTime(DateUtils.getDateTimeObject(event.getDate(), event.getStartTime()));
        this.endTime = DateUtils.getDateTimeObject(event.getDate(), event.getEndTime());
        //setEndTime(DateUtils.getDateTimeObject(event.getDate(), event.getEndTime()));
        this.locations = event.getLocations();
        //setLocations(event.getLocations());
        //this.className = event.getClassDetails().replace(";;",";");
        this.className = event.getClassDetails().split(";")[0];
        //setClassName(event.getClassDetails().split(";")[0]);
        this.classPrefix = className.split(" ")[0];
        //setClassPrefix(className.split(" ")[0]);
        this.faculty = event.getFaculty();
        //setFaculty(event.getFaculty());
        status = "initiated";
        this.isScheduled = false;
        canBeScheduled = true;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getFaculty() {
        return faculty;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    protected String getHour(DateTime date) {
        int hour = date.getHourOfDay();

        if (hour > 12)
            return Integer.toString(hour - 12);

        return Integer.toString(date.getHourOfDay());
    }

    protected String getMinute(DateTime date) {
        return Integer.toString(date.getMinuteOfHour());
    }

    protected int getCurrentYear() {
        return new DateTime().getYear();
    }

    protected int convertMonthAbbreviationToInt(String monthAbbreviation) {
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

    protected String getTimeOfDay(DateTime date) {
        DateTimeFormatter timeOfDay = DateTimeFormat.forPattern("a");
        return date.toString(timeOfDay);
    }

    /**
     * Return the date as a String in MM/DD/YYYY format
     * @param date a DateTime object
     * @return a String
     */
    protected String getDateInMDYFormat(DateTime date) {
        DateTimeFormatter MDYFmt = DateTimeFormat.forPattern("MM/dd/y");
        return date.toString(MDYFmt);
    }


    /**
     * Return the time in "h:mm a" format
     * @param date a DateTime object
     * @return a String
     */
    protected String getTime(DateTime date) {
        DateTimeFormatter time = DateTimeFormat.forPattern("hh:mm a");
        return date.toString(time);
    }

    protected String durationInMinutes(DateTime one, DateTime two) {
        org.joda.time.Duration duration = new org.joda.time.Duration(one, two);
        return Long.toString(duration.getStandardMinutes());
    }

    public List<String> getLocations() {
        return new ArrayList<>(locations);
    }


    public void setNeedsToBeScheduled(Boolean needsToBeScheduled) {
        this.needsToBeScheduled = needsToBeScheduled;
    }

    public boolean isNeedsToBeScheduled() {
        return needsToBeScheduled;
    }

    public void setCanBeScheduled(Boolean canBeScheduled) {
        this.canBeScheduled = canBeScheduled;
    }

    public boolean isCanBeScheduled() {
        return canBeScheduled;
    }

    public void setIsScheduled(Boolean isScheduled) {
        this.isScheduled = isScheduled;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public String toString() {
        return className + " " + StringUtils.join(locations, ",") + " " + getTime(startTime) + " " + getTime(endTime);
    }

    public static void main(String[] args) {
        String time = "10:00 AM � 10:50 AM";
        System.out.println(time.indexOf('�'));
    }
}
