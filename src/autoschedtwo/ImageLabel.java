package autoschedtwo;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by dmanzelmann on 6/9/2015.
 */
public class ImageLabel extends JLabel {

    public ImageLabel(BufferedImage image) {
        new ImageIcon(image);
    }
}
