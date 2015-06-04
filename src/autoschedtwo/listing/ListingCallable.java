package autoschedtwo.listing;

import autoschedtwo.Login;

import java.util.concurrent.Callable;

/**
 * Created by dmanzelmann on 6/4/2015.
 */
public class ListingCallable implements Callable<Listing> {
    Listing listing;
    Login login;

    public ListingCallable(Listing listing, Login login) {
        this.listing = listing;
        this.login = login;
    }

    public Listing call() {
        return listing.schedule(login.getUsername(), login.getPassword());
    }
}
