package autoschedtwo.listing;

import autoschedtwo.Scheduler;
import org.joda.time.DateTime;
import org.openqa.selenium.WebElement;

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
