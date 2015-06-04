package autoschedtwo;

import autoschedtwo.listing.Listing;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by dmanzelmann on 6/4/2015.
 */
public class AutoSchedListModel extends AbstractListModel<Listing> {
    ArrayList<Listing> list = new ArrayList<>();

    public int getSize() {
        return list.size();
    }

    public Listing getElementAt(int index) {
        return list.get(index);
    }

    public void addElement(Listing listing) {
        list.add(listing);
        fireIntervalAdded(this, list.size() - 1, list.size() - 1);
    }

}
