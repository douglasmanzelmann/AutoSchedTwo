package autoschedtwo;

import autoschedtwo.listing.Listing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dmanzelmann on 6/4/2015.
 */
public class AutoSchedListModel extends AbstractListModel<Listing> implements Iterable<Listing> {
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

    public void updateElement(Listing listing) {
        for (int i = 0; i < list.size(); i++) {
            // literally want to know if they are the same object, i.e., same address / reference
            if (list.get(i) == listing)
                fireContentsChanged(this, i, i);
        }
    }

    @Override
    public Iterator<Listing> iterator() {
        Iterator<Listing> it = new Iterator<Listing>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < list.size();
            }

            @Override
            public Listing next() {
                return list.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };

        return it;
    }
}
