package netflixstatistics;

// @AUTHOR Felix
import domain.Content;
import domain.Profile;
import domain.Seen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class FilmAccPanel extends JPanel {

    private List<Content> contents = new ArrayList<Content>();
    private List<Seen> seens = new ArrayList<Seen>();
    private DBConnect database;
    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JLabel profileNameLb, filmsWatchedLb;
    private JTextArea films;
    private JScrollPane scrollPane;

    private JPanel thisPanel;

    public FilmAccPanel(Profile profile) {
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
        profileNameLb = new JLabel(profile.getName());
        filmsWatchedLb = new JLabel("Films watched:");

        //Initializing textarea & scrollpanel
        films = new JTextArea();
        scrollPane = new JScrollPane(films);

        //Setting background color for buttons
        menuAccBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons & labels
        menuAccBtn.setForeground(Color.BLACK);//Is black because active
        profileNameLb.setForeground(Color.WHITE);
        filmsWatchedLb.setForeground(Color.WHITE);

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
        profileNameLb.setBounds(50, 30, 400, 25);
        filmsWatchedLb.setBounds(50, 55, 400, 25);
//            films.setBounds(50, 80, 400, 130);
        scrollPane.setBounds(50, 80, 400, 130);
        profileNameLb.setHorizontalAlignment(SwingConstants.CENTER);
        filmsWatchedLb.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting other properties
        profileNameLb.setFont(new Font("", Font.BOLD, 20));
        films.setMargin(new Insets(4, 6, 4, 6));
        scrollPane.setBackground(Color.WHITE);

        films.setEditable(false);
        films.setLineWrap(true);
        films.setWrapStyleWord(true);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getAllSeen();
        getAllContent();
        for (int i = 0; i < seens.size(); i++) {
            if (seens.get(i).getProfileNumber() == profile.getProfileNumber()) {
                for (int j = 0; j < contents.size(); j++) {
                    if (seens.get(i).getContentId() == contents.get(j).getContentID()) {
                        if (contents.get(j).getFilm() != null) {
                            films.append(contents.get(j).getFilm() + "\n");
                        }
                    }
                }
            }
        }

        //Adding buttons & textarea in contentpanel
        content.add(profileNameLb);
        content.add(filmsWatchedLb);
        content.add(scrollPane);

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

    public void getAllContent() {
        try {
            String theQuery = "SELECT * FROM `content`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                contents.add(new Content(Integer.parseInt(database.rs.getString("ContentID")),
                        database.rs.getString("Film"), database.rs.getString("TV_Show"),
                        database.rs.getString("SeasonCode"), database.rs.getString("Title"),
                        Integer.parseInt(database.rs.getString("AgeCategory")),
                        database.rs.getString("Language"), database.rs.getString("Duration"),
                        database.rs.getString("Genre"), database.rs.getString("SimilarTo")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void getAllSeen() {
        try {
            String theQuery = "SELECT * FROM `seen`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                seens.add(new Seen(Integer.parseInt(database.rs.getString("SeenID")),
                        Integer.parseInt(database.rs.getString("ProfileNumber")),
                        Integer.parseInt(database.rs.getString("SubscriberNumber")),
                        Integer.parseInt(database.rs.getString("ContentID")),
                        Float.parseFloat(database.rs.getString("Percentage"))));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
