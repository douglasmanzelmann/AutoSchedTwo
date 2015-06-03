package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.MediasiteListing;

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

    public static String getUsername(Listing listing) {
        if (listing instanceof MediasiteListing)

    }
}
