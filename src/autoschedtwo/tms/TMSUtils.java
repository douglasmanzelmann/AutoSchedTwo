package autoschedtwo.tms;

/**
 * Created by dmanzelmann on 5/22/2015.
 */
public class TMSUtils {
    public static String chooseBaltimoreCodec(String baltimoreRoom) {
        if (baltimoreRoom.contains("PH N1"))
            return "VTC1";
        else if (baltimoreRoom.contains("PH N2"))
            return "VTC2";
        else if (baltimoreRoom.contains("PH N3"))
            return "VTC3";
        else if (baltimoreRoom.contains("PH S201"))
            return "UMB 201";
        else if (baltimoreRoom.contains("PH N208"))
            return "VTC-CART1";

        return "VTC4";
    }
}
