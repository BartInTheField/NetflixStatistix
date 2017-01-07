package netflixstatistics;

// @AUTHOR Felix
import domain.Content;
import domain.Seen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

class ShowPanel extends JPanel {
 
    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JLabel titleStatic, accWatchedStatic, averageWatchedStatic;
    private JLabel title, accWatched, averageWatched;
    private JComboBox showBox, episodeBox;
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private NSButton showStats;

    private JPanel thisPanel;

    private DBConnect database;
    private ArrayList<Content> contents = new ArrayList<>();
    private ArrayList<Seen> seens = new ArrayList<>();
    private Seen selectedSeen;

    public ShowPanel() {

        thisPanel = this;
        database = new DBConnect();

        //Setting layout for hole panelx
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
        showStats = new NSButton("Show statistics");

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
        menuShowBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons
        menuShowBtn.setForeground(Color.BLACK);//Is black because active

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

        //Initializing combobox
        showBox = new JComboBox(model);
        episodeBox = new JComboBox();

        //Initializing static labels
        titleStatic = new JLabel("Title:");
        accWatchedStatic = new JLabel("How many profiles have watched:");
        averageWatchedStatic = new JLabel("Average percentage of time watched:");

        //Initializing database labels
        title = new JLabel("");
        accWatched = new JLabel("");
        averageWatched = new JLabel("");

        //Setting label colors to white 
        titleStatic.setForeground(Color.WHITE);
        accWatchedStatic.setForeground(Color.WHITE);
        averageWatchedStatic.setForeground(Color.WHITE);
        title.setForeground(Color.WHITE);
        accWatched.setForeground(Color.WHITE);
        averageWatched.setForeground(Color.WHITE);

        //Setting location of combobox
        showBox.setBounds(50, 30, 400, 25);
        episodeBox.setBounds(50, 65, 400, 25);

        //Setting location of  static labels
        titleStatic.setBounds(50, 100, 40, 25);
        accWatchedStatic.setBounds(50, 125, 200, 25);
        averageWatchedStatic.setBounds(50, 150, 220, 25);

        //Setting location of button
        showStats.setBounds(50, 180, 400, 25);

        //Setting location of  nonstatic labels
        title.setBounds(100, 100, 350, 25);
        accWatched.setBounds(350, 125, 100, 25);
        averageWatched.setBounds(350, 150, 100, 25);

        //Setting alignment for nonstatic labels
        title.setHorizontalAlignment(SwingConstants.RIGHT);
        accWatched.setHorizontalAlignment(SwingConstants.RIGHT);
        averageWatched.setHorizontalAlignment(SwingConstants.RIGHT);

        //Get all content and add it to combobox
        getAllContent();
        getAllSeen();
        String selectShowMsg = "- Select show -";
        String selectEpiMsg = "- Select episode -";

        showBox.addItem(selectShowMsg);
        episodeBox.addItem(selectEpiMsg);
        for (int i = 0; i < contents.size(); i++) {
            if (model.getIndexOf(contents.get(i).getTvShow()) == -1) {
                showBox.addItem(contents.get(i).getTvShow());
            }
        }

        ShowDropDownHandler showDropDownHandler = new ShowDropDownHandler();
        showBox.addActionListener(showDropDownHandler);
        EpiDropDownHandler epiDropDownHandler = new EpiDropDownHandler();
        episodeBox.addActionListener(epiDropDownHandler);
        ButtonHandler handler = new ButtonHandler();
        showStats.addActionListener(handler);

        //Adding combobox in contentpanel
        content.add(showBox);
        content.add(episodeBox);

        //Adding labels in contentpanel
        content.add(titleStatic);
        content.add(accWatchedStatic);
        content.add(averageWatchedStatic);
        content.add(title);
        content.add(accWatched);
        content.add(averageWatched);
        content.add(showStats);

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
            accWatched.setText("");
            averageWatched.setText("");

            if (showBox.getItemAt(0).equals("< Select show >")) {
                showBox.removeItemAt(0);
            }

            //Add all content to the show combobox
            for (int i = 0; i < contents.size(); i++) {
                if (showBox.getSelectedItem().equals(contents.get(i).getTvShow())) {
                    episodeBox.addItem(contents.get(i).getSeasonCode());
                }

            }
        }
    }

    class EpiDropDownHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            title.setText("");
            accWatched.setText("");
            averageWatched.setText("");
//            episodeBox.removeItemAt(0);
        }
    }

    class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Get title and display it
            for (int i = 0; i < contents.size(); i++) {
                if (episodeBox.getSelectedItem().equals(contents.get(i).getSeasonCode())) {
                    title.setText(contents.get(i).getTitle());
                }
            }
            //Count number of profiles that have watched the selected episode of show
            //Also count the percentage and add them all up in totalPerc
            int counter = 0;
            float totalPerc = 0;
            for (int i = 0; i < contents.size(); i++) {
                if (showBox.getSelectedItem().equals(contents.get(i).getTvShow()) && episodeBox.getSelectedItem().equals(contents.get(i).getSeasonCode())) {
                    for (int j = 0; j < seens.size(); j++) {
                        if (contents.get(i).getContentID() == seens.get(j).getContentId()) {
                            counter++;
                            totalPerc += seens.get(j).getPercentage();
                        }
                    }
                }
            }
            accWatched.setText(counter + "");
            totalPerc /= counter;
            if (counter == 0) {
                averageWatched.setText(0.0 + "%");
            } else {
                averageWatched.setText(totalPerc + "%");
            }
        }
    }

    public void getAllContent() {
        try {
            String theQuery = "SELECT * FROM content WHERE Film IS NULL";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                contents.add(new Content(Integer.parseInt(database.rs.getString("ContentID")),
                        database.rs.getString("TV_Show"),
                        database.rs.getString("SeasonCode"), database.rs.getString("Title")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void getAllSeen() {
        try {
            String theQuery = "SELECT SeenID, ProfileNumber, SubscriberNumber, seen.ContentID, Percentage\n"
                    + "FROM seen\n"
                    + "INNER JOIN content\n"
                    + "ON seen.ContentID = content.ContentID\n"
                    + "WHERE Film IS NULL";
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
