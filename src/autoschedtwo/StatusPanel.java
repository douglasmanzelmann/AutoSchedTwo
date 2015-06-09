package autoschedtwo;

import autoschedtwo.listing.Listing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmanzelmann on 6/9/2015.
 */
public class StatusPanel {
    Listing listing;
    JLabel imageLabel;

    public <L extends Listing> StatusPanel(L l) {
        this.listing = l;
        imageLabel = new JLabel(new ImageIcon(listing.getStatusScreenshot()));

        createAndShowGui();
    }

    private void createAndShowGui() {
        JDialog statusDialog = new JDialog();
        statusDialog.setLayout(new BorderLayout());
        statusDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        statusDialog.add(imageLabel, BorderLayout.CENTER);
        statusDialog.pack();
        statusDialog.setLocationByPlatform(true);
        statusDialog.setVisible(true);
    }

}
