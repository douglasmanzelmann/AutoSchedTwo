package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingFactory;
import autoschedtwo.portal.PortalDriver;
import autoschedtwo.portal.PortalScheduleEventsEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private JTextField portalUserNameField = new JTextField(10);
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
    private DefaultListModel<Listing> listingDefaultListModel = new DefaultListModel<>();
    private JList<Listing> listingJList = new JList<>(listingDefaultListModel);

    // Main panel
    private JPanel contentPanel = new JPanel(new BorderLayout());

    public AutoSchedGUI() {
         portalLoginPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(
                                EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY),
                        "Portal / Mediasite Login"
                )
        );
        portalLoginPanel.add(portalUserNameLabel);
        portalLoginPanel.add(portalUserNameField);
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
        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateList();
            }
        });

        listScrollPane.setViewportView(listingJList);
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

    private void populateList() {
        String username = portalUserNameField.getText();
        String password = new String(portalPasswordField.getPassword());
        int year = Integer.parseInt(schedYearText.getText());
        int month = Integer.parseInt(schedMonthText.getText());
        int day = Integer.parseInt(schedDayText.getText());

        AutoSchedWorker autoSchedWorker = new AutoSchedWorker(username, password,
                year, month, day);
        autoSchedWorker.execute();

        //try {
        //    autoSchedWorker.get();
        //} catch (InterruptedException | ExecutionException ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }

    private class AutoSchedWorker extends SwingWorker<Void, Listing> {
        private ChromeOptions options = new ChromeOptions();
        private WebDriver driver;
        private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        private String username;
        private String password;
        private int year;
        private int month;
        private int day;
        private List<Listing> tempList;

        public AutoSchedWorker(String username,
                               String password, int year, int month, int day) {
            System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
            this.username = username;
            this.password = password;
            this.year = year;
            this.month = month;
            this.day = day;
            this.tempList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground() throws Exception {
            this.options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
            PortalDriver portalDriver = new PortalDriver(driver);
            java.util.List<PortalScheduleEventsEvent> portalScheduleEventsEventList =
                    portalDriver.getScheduleElements(username, password, year, month, day);

            for (PortalScheduleEventsEvent event : portalScheduleEventsEventList) {
                tempList.add(ListingFactory.createListing(event));

            }
            return null;
        }

        @Override
        protected void done() {
            for (Listing listing : tempList) {
                listingDefaultListModel.addElement(listing);
            }
        }
    }
}
