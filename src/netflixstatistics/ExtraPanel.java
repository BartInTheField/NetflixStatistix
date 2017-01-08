package netflixstatistics;

// @AUTHOR Felix
import domain.Account;
import domain.Profile;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class ExtraPanel extends JPanel {

    private Profile selectedProfile;
    private List<Profile> profiles = new ArrayList<Profile>();
    private Account selectedAccount;
    private List<Account> accountlist = new ArrayList<Account>();

    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JLabel accountsLb, longestMovieLb;
    private JTextArea accounts, longestMovie;
    private JScrollPane scrollPane;

    private DBConnect database;

    private JPanel thisPanel;

    public ExtraPanel() {
        database = new DBConnect();
        thisPanel = this;

        //Setting layout for whole panel
        setLayout(new BorderLayout());

        //Banner
        banner = new BannerPanel();

        //Setting dimension for banner
        banner.setPreferredSize(new Dimension(600, 150));

        //Menu
        menu = new JPanel();

        //Initializing buttons
        menuAccBtn = new NSButton("Account");
        menuFilmBtn = new NSButton("Film");
        menuShowBtn = new NSButton("Show");
        menuExtraBtn = new NSButton("Extra");
        menuConfigBtn = new NSButton("Config");

        //Adding handlers to buttons
        MenuAccBtnHandler menuAccBtnHandler = new MenuAccBtnHandler();
        menuAccBtn.addActionListener(menuAccBtnHandler);
        MenuFilmBtnHandler menuFilmBtnHandler = new MenuFilmBtnHandler();
        menuFilmBtn.addActionListener(menuFilmBtnHandler);
        MenuShowBtnHandler menuShowBtnHandler = new MenuShowBtnHandler();
        menuShowBtn.addActionListener(menuShowBtnHandler);
        MenuExtraBtnHandler extraBtnHandler = new MenuExtraBtnHandler();
        menuExtraBtn.addActionListener(extraBtnHandler);
        MenuConfigBtnHandler configBtnHandler = new MenuConfigBtnHandler();
        menuConfigBtn.addActionListener(configBtnHandler);

        //Initializing labels
        accountsLb = new JLabel("Accounts with 1 profile:");
        longestMovieLb = new JLabel("Movie with longest time for age under 16:");

        //Initializing textarea & scrollpanel
        accounts = new JTextArea();
        longestMovie = new JTextArea();
        scrollPane = new JScrollPane(accounts);

        //Setting background color for buttons
        menuExtraBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons & labels
        menuExtraBtn.setForeground(Color.BLACK);//Is black because active
        accountsLb.setForeground(Color.WHITE);
        longestMovieLb.setForeground(Color.WHITE);

        //adding buttons in menupanel
        menu.add(menuAccBtn);
        menu.add(menuFilmBtn);
        menu.add(menuShowBtn);
        menu.add(menuExtraBtn);
        menu.add(menuConfigBtn);
        menu.setLayout(new GridLayout(5, 1));

        //content
        content = new JPanel();
        content.setSize(600, 250);
        content.setBackground(Color.BLACK);

        //setting layout null so setBounds works
        content.setLayout(null);

        //Setting location of labels & textarea
        accountsLb.setBounds(50, 30, 400, 25);
        scrollPane.setBounds(50, 60, 400, 80);
        longestMovieLb.setBounds(50, 150, 400, 25);
        longestMovie.setBounds(50, 180, 400, 25);
        accountsLb.setHorizontalAlignment(SwingConstants.CENTER);
        longestMovieLb.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting other properties
        accountsLb.setFont(new Font("", Font.BOLD, 12));
        accounts.setMargin(new Insets(4, 6, 4, 6));
        accounts.setEditable(false);
        longestMovie.setMargin(new Insets(4, 6, 4, 6));
        longestMovie.setEditable(false);
        scrollPane.setBackground(Color.WHITE);

        accounts.setLineWrap(true);
        accounts.setWrapStyleWord(true);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getAllAccounts();
        getAllProfiles();
        
        

        //Filling textareas with data
        getLongestMovie();
        
        //Filling the account with 1 profile
        for (int i = 0; i < accountlist.size(); i++) {
            int count = 0;
            for (int j = 0; j < profiles.size(); j++) {
                if (accountlist.get(i).getSubscriberNumber() == profiles.get(j).getSubscriberNumber()) {
                    count++;
                    if (count == 1) {
                        accounts.append(accountlist.get(i).getSubscriberNumber() + "  "
                                + accountlist.get(i).getName() + "\n");
                    }
                    if (count > 1 || count == 0) {
                        accounts.setText(accounts.getText().replaceAll(accountlist.get(i).getSubscriberNumber() + "  "
                                + accountlist.get(i).getName() + "\n", ""));
                    }
                }
            }
        }
        if (accounts.getText().equals("")) {
            accounts.setText("Currently there aren't any accounts with only 1 profile.");
        }

        //Adding buttons & textarea in contentpanel
        content.add(accountsLb);
        content.add(scrollPane);
        content.add(longestMovieLb);
        content.add(longestMovie);

        //Adding panels to Accountpanel
        add(banner, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(menu, BorderLayout.WEST);
    }

    //Handlers for menu buttons
    class MenuAccBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new GUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    class MenuFilmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new FilmGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    class MenuShowBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ShowGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    class MenuExtraBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ExtraGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    class MenuConfigBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    public void getLongestMovie() {
        try {
            String theQuery = "SELECT ContentID, Film, AgeCategory, Language, Duration, Genre\n"
                    + "FROM content\n"
                    + "WHERE AgeCategory < 16 AND Title IS NULL\n"
                    + "ORDER BY Duration DESC\n"
                    + "LIMIT 1;";
            database.rs = database.st.executeQuery(theQuery);

            if (database.rs.next()) {
                String contentId = database.rs.getString("ContentId");
                String film = database.rs.getString("Film");
                String ageCategory = database.rs.getString("AgeCategory");
                String language = database.rs.getString("Language");
                String duration = database.rs.getString("Duration");
                String genre = database.rs.getString("Genre");

                longestMovie.setText(contentId + ". '" + film + "' | Age " + ageCategory + " | " + language + " | " + duration + " mins | " + genre);
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void getAllProfiles() {
        try {
            String theQuery = "SELECT * FROM `profile`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                profiles.add(new Profile(Integer.parseInt(database.rs.getString("ProfileNumber")),
                        Integer.parseInt(database.rs.getString("SubscriberNumber")), database.rs.getString("Name"),
                        database.rs.getString("Birthday")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void getAllAccounts() {
        try {
            String theQuery = "SELECT * FROM `account`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                accountlist.add(new Account(Integer.parseInt(database.rs.getString("SubscriberNumber")),
                        database.rs.getString("Name"), database.rs.getString("Street"),
                        database.rs.getString("PostalCode"), Integer.parseInt(database.rs.getString("StreetNumber")),
                        database.rs.getString("City"), database.rs.getString("Birthday")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
