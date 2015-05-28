package autoschedtwo;

import autoschedtwo.listing.Listing;

import javax.management.remote.JMXConnectorFactory;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by dmanzelmann on 5/26/2015.
 */
public class AutoSchedGUI {
    // Main panel
    private JPanel readSchedPanel = new JPanel();

    // Main panel for Login info
    private JPanel portalLoginPanel = new JPanel();
    private JLabel portalUserNameLabel = new JLabel("Portal/Mediasite Username");
    private JLabel portalPasswordLabel = new JLabel("Portal/Mediasite Password");
    private JComboBox portalUserNameBox = new JComboBox();
    private JPasswordField portalPasswordField = new JPasswordField(10);

    private JPanel tmsLoginPanel = new JPanel();
    private JLabel tmsUserNameLabel = new JLabel("TMS Username");
    private JLabel tmsPasswordLabel = new JLabel("TMS Password");
    private JTextField tmsUserNameField = new JTextField(10);
    private JPasswordField tmsPasswordField = new JPasswordField(10);
    private JButton startbutton = new JButton("Start");

    // Main panel for dates
    private JPanel readschedDatesPanel = new JPanel();
    private JTextField schedMonthText = new JTextField("Month", 5);
    private JTextField schedDayText = new JTextField("Day", 5);
    private JTextField schedYearText = new JTextField("Year", 5);

    // ScrollPanel for list
    private JScrollPane listScrollPane = new JScrollPane();
    private DefaultListModel<Listing> listingDefaultListModel;
    private JList<Listing> listingJList;

    // Main panel
    private JPanel contentPanel = new JPanel(new BorderLayout());

    public AutoSchedGUI() {
        listingDefaultListModel = new DefaultListModel<>();
        listingJList = new JList<>(listingDefaultListModel);

        portalLoginPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY),
                        "Portal / Mediasite Login"
                )
        );
        portalLoginPanel.add(portalUserNameLabel);
        portalLoginPanel.add(portalUserNameBox);
        portalLoginPanel.add(portalPasswordLabel);
        portalLoginPanel.add(portalPasswordField);

        tmsLoginPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY),
                        "TMS Login"
                )
        );
        tmsLoginPanel.add(tmsUserNameLabel);
        tmsLoginPanel.add(tmsUserNameField);
        tmsLoginPanel.add(tmsPasswordLabel);
        tmsLoginPanel.add(tmsPasswordField);


        readschedDatesPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY),
                        "Schedule Week"
                )
        );
        readschedDatesPanel.add(schedMonthText);
        readschedDatesPanel.add(schedDayText);
        readschedDatesPanel.add(schedYearText);

        readSchedPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY),
                                "Read Schedule"
                )
        );
        readSchedPanel.add(portalLoginPanel);
        readSchedPanel.add(tmsLoginPanel);
        readSchedPanel.add(readschedDatesPanel);
        readSchedPanel.add(startbutton);


        listScrollPane.add(listingJList);
        contentPanel.add(readSchedPanel, BorderLayout.PAGE_START);
        contentPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    public JComponent getMainComponent() {
        return contentPanel;
    }

    public static void createAndShowGui() {
        AutoSchedGUI autoSchedGUI = new AutoSchedGUI();

        JFrame frame = new JFrame("AutoSchedTwo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(autoSchedGUI.getMainComponent());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }
}
