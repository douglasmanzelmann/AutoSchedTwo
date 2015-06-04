package autoschedtwo;

import autoschedtwo.listing.Listing;
import autoschedtwo.listing.ListingCallable;
import autoschedtwo.listing.MediasiteListing;
import autoschedtwo.listing.TMSListing;
import autoschedtwo.portal.PortalDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.*;

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
    private AutoSchedListModel listingListModel = new AutoSchedListModel();
    private JList<Listing> listingJList = new JList<>(listingListModel);

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
        String portalUsername = portalUserNameField.getText();
        String portalPassword = new String(portalPasswordField.getPassword());
        Login portalLogin = new Login(portalUsername, portalPassword);
        String tmsUsername = tmsUserNameField.getText();
        String tmsPassword = new String(tmsPasswordField.getPassword());
        Login tmsLogin = new Login(tmsUsername, tmsPassword);
        int year = Integer.parseInt(schedYearText.getText());
        int month = Integer.parseInt(schedMonthText.getText());
        int day = Integer.parseInt(schedDayText.getText());

        ReadSchedWorker readSchedWorker = new ReadSchedWorker(portalLogin, tmsLogin,
                year, month, day);
        readSchedWorker.execute();

        while (!readSchedWorker.isDone()) { }

        /*ScheduleActivityWorker scheduleActivityWorker =
                new ScheduleActivityWorker(portalLogin,tmsLogin);
        scheduleActivityWorker.execute();*/


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }

    private class ReadSchedWorker extends SwingWorker<Void, Listing> {
        private ChromeOptions options = new ChromeOptions();
        private WebDriver driver;
        private LinkedBlockingQueue<Future<Listing>> listingQueue;
        private LoginFactory loginFactory;
        private int year;
        private int month;
        private int day;

        public ReadSchedWorker(Login mediasiteLogin, Login tmsLogin,
                               int year, int month, int day) {
            System.setProperty("webdriver.chrome.driver", "\\\\private\\Home\\Desktop\\chromedriver.exe");
            listingQueue = new LinkedBlockingQueue<>();
            loginFactory = new LoginFactory(mediasiteLogin, tmsLogin);
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        protected Void doInBackground() throws Exception {
            this.options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);

            String portalUsername = loginFactory.getPortalUsername();
            String portalPassword = loginFactory.getPortalPassword();
            PortalDriver portalDriver = new PortalDriver(driver, listingQueue);
            portalDriver.getScheduleElements(portalUsername, portalPassword, year, month, day);

            while (!listingQueue.isEmpty()) {
                Future<Listing> futureListing = listingQueue.poll();
                Listing listing = futureListing.get();
                publish(listing);

            }
            return null;
        }

        @Override
        protected void process(List<Listing> listings) {
            for (Listing listingItem : listings) {
                listingListModel.addElement(listingItem);
            }
        }
    }

    private class ScheduleActivityWorker extends SwingWorker<Void, Listing> {
        private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        private ExecutorCompletionService<Listing> completionService = new ExecutorCompletionService<Listing>(executor);
        private LoginFactory loginFactory;
        private int numberOfTasks;

        public ScheduleActivityWorker(Login mediasiteLogin, Login tmsLogin) {
            loginFactory = new LoginFactory(mediasiteLogin, tmsLogin);
            numberOfTasks = 0;
            System.out.println("in schedactivity constructor");
        }

        @Override
        protected Void doInBackground() {
            for (Listing listing : listingListModel) {
                if (listing instanceof MediasiteListing || listing instanceof TMSListing) {
                    Callable<Listing> callable = new ListingCallable(listing, loginFactory.getLogin(listing));
                    completionService.submit(callable);
                    numberOfTasks++;
                }
            }

            for (int i = 0; i < numberOfTasks; i++) {
                try {
                    Listing listing = completionService.take().get();
                    publish(listing);
                }  catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
            }

            return null;
        }

        @Override
        protected void process(List<Listing> listings) {
            for (Listing listing : listings) {
                listingListModel.updateElement(listing);
            }
        }
    }
}
