package autoschedtwo;

import autoschedtwo.GuiUtils.AutoSchedListModel;
import autoschedtwo.GuiUtils.ListingCellRenderer;
import autoschedtwo.GuiUtils.StatusPanel;
import autoschedtwo.listing.Listing;
import autoschedtwo.portal.PortalDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private AutoSchedListModel<Listing> listingListModel = new AutoSchedListModel<>();
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

        listingJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int NUMBER_OF_CLICKS_TO_OPEN = 2;
                JList<Listing> list = (JList<Listing>) e.getSource();
                if (e.getClickCount() == NUMBER_OF_CLICKS_TO_OPEN) {
                    int index = list.locationToIndex(e.getPoint());
                    new StatusPanel(listingListModel.getElementAt(index));
                }
            }
        });
        listingJList.setCellRenderer(new ListingCellRenderer());
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        SchedWorker schedWorker = new SchedWorker(portalLogin, tmsLogin,
                year, month, day);
        schedWorker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }

    private class SchedWorker extends SwingWorker<Void, Listing> {
        private ChromeOptions options = new ChromeOptions();
        private WebDriver driver;
        private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);
        private LinkedBlockingQueue<Future<Listing>> listingQueue;
        private LoginFactory loginFactory;
        private int year;
        private int month;
        private int day;

        public SchedWorker(Login mediasiteLogin, Login tmsLogin,
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
            PortalDriver portalDriver = new PortalDriver(driver, listingQueue, executor);
            portalDriver.getScheduleElements(portalUsername, portalPassword, year, month, day);

            while (!listingQueue.isEmpty()) {
                Future<Listing> futureListing = listingQueue.take();
                Listing listing = futureListing.get();
                publish(listing);
                /*if (listing.isNeedsToBeScheduled() && listing.isCanBeScheduled()) {
                    SchedActivityWorker schedActivityWorker =
                            new SchedActivityWorker(listing,
                                    loginFactory.getLogin(listing));
                    executor.submit(schedActivityWorker);
                }*/
            }

            executor.shutdown();
            return null;
        }

        @Override
        protected void process(List<Listing> listings) {
            for (Listing listingItem : listings) {
                listingListModel.addElement(listingItem);
            }
        }

        @Override
        protected void done() {
            try {
                this.get();
            } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        }


    }

    private class SchedActivityWorker extends SwingWorker<Void, Listing> {
        private Listing listing;
        private Login login;

        public SchedActivityWorker(Listing listing, Login login) {
            this.listing = listing;
            this.login = login;
        }

        @Override
        protected Void doInBackground() {
            publish(listing.schedule(login.getUsername(), login.getPassword()));
            return null;
        }

        @Override
        protected void process(List<Listing> listings) {
            for (Listing listing : listings)
                listingListModel.updateElement(listing);
        }

        @Override
        protected void done() {
            try {
                this.get();
            } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        }
    }
}
