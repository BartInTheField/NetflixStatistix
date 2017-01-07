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

class ShowAccPanel extends JPanel {

    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private DBConnect database;
    private List<Content> contents = new ArrayList<Content>();
    private Content selectedContent;
    private List<Seen> seens = new ArrayList<Seen>();

    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JLabel profileNameLb, showsWatched, titleStatic, averageWatchedStatic;
    private JLabel title, averageWatched, pointerShow;
    private JComboBox showBox, episodeBox;
    private NSButton showStats;

    private JPanel thisPanel;

    public ShowAccPanel(Profile profile) {
        database = new DBConnect();
        thisPanel = this;

        //Setting layout for hole panel
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

        //Setting background color for buttons
        menuAccBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons
        menuAccBtn.setForeground(Color.BLACK);//Is black because active

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

        //Initializing buttons
        showStats = new NSButton("Show statistics");

        //Initializing combobox
        showBox = new JComboBox(model);
        episodeBox = new JComboBox();

        //Initializing static labels
        profileNameLb = new JLabel(profile.getName());
        showsWatched = new JLabel("Shows watched:");
        titleStatic = new JLabel("Title:");
        averageWatchedStatic = new JLabel("Percentage watched:");
        pointerShow = new JLabel("<html><span style='font-size:22; font-family: agency fb'>></span></html>");

        //Initializing database labels
        title = new JLabel("");
        averageWatched = new JLabel("");

        //Setting label colors to white 
        pointerShow.setForeground(Color.WHITE);
        showsWatched.setForeground(Color.WHITE);
        profileNameLb.setForeground(Color.WHITE);
        titleStatic.setForeground(Color.WHITE);
        averageWatchedStatic.setForeground(Color.WHITE);
        title.setForeground(Color.WHITE);
        averageWatched.setForeground(Color.WHITE);

        //Setting location of button
        showStats.setBounds(50, 170, 400, 25);

        //Setting location of combobox
        showBox.setBounds(50, 50, 400, 25);
        episodeBox.setBounds(50, 85, 400, 25);

        //Setting location of  static labelsa
        profileNameLb.setBounds(50, 10, 400, 25);
        showsWatched.setBounds(50, 35, 400, 10);
        titleStatic.setBounds(50, 115, 200, 25);
        averageWatchedStatic.setBounds(50, 140, 220, 25);
        pointerShow.setBounds(36, 50, 10, 25);

        //Setting location of  nonstatic labels
        title.setBounds(350, 115, 100, 25);
        averageWatched.setBounds(350, 140, 100, 25);

        profileNameLb.setFont(new Font("", Font.BOLD, 20));
        profileNameLb.setHorizontalAlignment(SwingConstants.CENTER);
        showsWatched.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting alignment for nonstatic labels
        title.setHorizontalAlignment(SwingConstants.RIGHT);
        averageWatched.setHorizontalAlignment(SwingConstants.RIGHT);

        getAllContent();
        String selectShowMsg = "- Select show -";
        String selectEpiMsg = "- Select episode -";

        showBox.addItem(selectShowMsg);
        episodeBox.addItem(selectEpiMsg);
        //Get all seen
        try {
            String theQuery = "SELECT * FROM `seen` WHERE ProfileNumber = '" + profile.getProfileNumber() + "'";
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

        for (int i = 0; i < seens.size(); i++) {
            for (int j = 0; j < contents.size(); j++) {
                if (seens.get(i).getProfileNumber() == profile.getProfileNumber()) {
                    if (seens.get(i).getContentId() == contents.get(j).getContentID()) {
                        if (contents.get(j).getTvShow() != null) {
                            if (model.getIndexOf(contents.get(j).getTvShow()) == -1) {
                                showBox.addItem(contents.get(j).getTvShow());
                            }
                        }
                    }
                }
            }
        }

        ShowDropDownHandler dropDownHandler = new ShowDropDownHandler();
        showBox.addActionListener(dropDownHandler);
        EpiDropDownHandler epiDownHandler = new EpiDropDownHandler();
        episodeBox.addActionListener(epiDownHandler);

        //Adding combobox in contentpanel
        content.add(showBox);
        content.add(episodeBox);

        ButtonHandler buttonHandler = new ButtonHandler();
        showStats.addActionListener(buttonHandler);

        //Adding button in contentpanel
        content.add(showStats);

        //Adding labels in contentpanel
        content.add(pointerShow);
        content.add(showsWatched);
        content.add(profileNameLb);
        content.add(titleStatic);
        content.add(averageWatchedStatic);
        content.add(title);
        content.add(averageWatched);

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

    class ShowDropDownHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            episodeBox.removeAllItems();
            title.setText("");
            averageWatched.setText("");
            pointerShow.setText("");
            
            if (showBox.getItemAt(0).equals("- Select show -")) {
                showBox.removeItemAt(0);
            }

            for (int i = 0; i < contents.size(); i++) {
                if (showBox.getSelectedItem().equals(contents.get(i).getTvShow())) {
                    episodeBox.addItem(contents.get(i).getSeasonCode());
                }
            }
        }
    }

    class EpiDropDownHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            title.setText("");
            averageWatched.setText("");
        }
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contents.size(); i++) {
                if (showBox.getSelectedItem().equals(contents.get(i).getTvShow())
                        && episodeBox.getSelectedItem().equals(contents.get(i).getSeasonCode())) {
                    title.setText(contents.get(i).getTitle());
                    for (int j = 0; j < seens.size(); j++) {
                        if (seens.get(j).getContentId() == contents.get(i).getContentID()) {
                            averageWatched.setText(seens.get(j).getPercentage() + "%");

                        } else {
                            averageWatched.setText("0.0%");
                        }
                    }
                }
            }
        }
    }

    //Database handlers
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
}
