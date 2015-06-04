package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.MediasiteListing;
import autoschedtwo.listing.TMSListing;

/**
 * Created by test on 6/2/2015.
 */
public class LoginFactory {
    private final Login mediasiteLogin;
    private final Login tmsLogin;

    public LoginFactory(Login mediasiteLogin, Login tmsLogin) {
        this.mediasiteLogin = mediasiteLogin;
        this.tmsLogin = tmsLogin;
    }

    public final String getUsername(Listing listing) {
        if (listing instanceof MediasiteListing)
            return mediasiteLogin.getUsername();
        else if (listing instanceof TMSListing)
            return tmsLogin.getUsername();

        return "";
    }

    public final String getPassword(Listing listing) {
        if (listing instanceof MediasiteListing)
            return mediasiteLogin.getPassword();
        else if (listing instanceof TMSListing)
            return tmsLogin.getPassword();

        return "";
    }

    public final String getPortalUsername() {
        return mediasiteLogin.getUsername();
    }

    public final String getPortalPassword() {
        return mediasiteLogin.getPassword();
    }
}
