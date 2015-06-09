package autoschedtwo.GuiUtils;

import autoschedtwo.listing.Listing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmanzelmann on 6/8/2015.
 */
public class ListingCellRenderer extends JPanel implements ListCellRenderer<Listing> {
    JLabel activty;
    JButton status;
    JLabel date;
    JLabel classDetails;
    Color background;

    public ListingCellRenderer() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        activty = new JLabel();
        status = new JButton();
        date = new JLabel();
        classDetails = new JLabel();

        add(status);
        add(activty);
        add(date);
        add(classDetails);
    }

    private void initComponents(Listing listing) {
        activty.setText(listing.getActivity());
        status.setBackground(setStatus(listing.getStatus()));
        date.setText(listing.getStartTime().toString());
        classDetails.setText(listing.getClassName());
    }

    public Color setStatus(String statusState) {
        switch (statusState) {
            case "failed":
                return Color.RED;
            case "initiated":
                return Color.WHITE;
            case "finished":
                return new Color(0, 128, 0);
            case "NA":
                return Color.GRAY;
        }

        return  null;
    }

    public Component getListCellRendererComponent(
            JList<? extends Listing> list,
            Listing listing,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        initComponents(listing);
        if (isSelected)
            background = Color.WHITE;
        else
            background = Color.LIGHT_GRAY;

        setBackground(background);

        return this;
    }
}
