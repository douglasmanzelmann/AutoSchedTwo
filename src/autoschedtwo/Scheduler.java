package autoschedtwo;

import org.openqa.selenium.WebDriver;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public interface Scheduler {

    public void schedule(String portalUsername, String portalPassword, String tmsUsername, String tmsPassword);
}
