package netflixstatistics;

// @AUTHOR Felix
import domain.Seen;
import domain.Content;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class FilmPanel extends JPanel {

    private Content selectedContent;
    private List<Content> contents = new ArrayList<>();
    private List<Seen> seens = new ArrayList<>();

    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JComboBox contentBox;
    private JLabel nrOfUsersLb, nrOfUsers;

    private JPanel thisPanel;

    private DBConnect database;

    public FilmPanel() {
        thisPanel = this;
        database = new DBConnect();
        contents = new ArrayList<>();
        seens = new ArrayList<>();

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

        //Initializing labels
        nrOfUsersLb = new JLabel("How many users watched this movie 100%?");
        nrOfUsers = new JLabel("");

        //Setting background color for buttons
        menuFilmBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons & labels
        menuFilmBtn.setForeground(Color.BLACK);//Is black because active
        nrOfUsersLb.setForeground(Color.WHITE);
        nrOfUsers.setForeground(Color.WHITE);

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

        //Initializing buttons and combobox
        contentBox = new JComboBox();

        //Setting location of buttons
        contentBox.setBounds(50, 30, 400, 25);

        getAllContent();
        getAllSeen();
        for (int i = 0; i < contents.size(); i++) {
            contentBox.addItem(contents.get(i).getFilm());
        }

        DropDownHandler dropDownHandler = new DropDownHandler();
        contentBox.addActionListener(dropDownHandler);

        //Setting location of labels
        nrOfUsersLb.setBounds(50, 60, 400, 25);
        nrOfUsers.setBounds(370, 60, 80, 25);
        nrOfUsers.setHorizontalAlignment(SwingConstants.RIGHT);
        
        //Adding buttons in contentpanel
        content.add(contentBox);
        content.add(nrOfUsersLb);
        content.add(nrOfUsers);

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

    class DropDownHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < contents.size(); i++) {
                if (contentBox.getSelectedItem().toString().equals(contents.get(i).getFilm())) {
                    selectedContent = contents.get(i);
                    break;
                }
            }
            countViews();
        }
    }

    public void getAllContent() {
        try {
            String theQuery = "SELECT DISTINCT content.ContentId, content.Film, seen.Percentage\n"
                    + "FROM content\n"
                    + "JOIN seen\n"
                    + "ON seen.ContentID = content.ContentID\n"
                    + "WHERE TV_show IS NULL AND Percentage = 100.0  \n"
                    + "ORDER BY `ContentId` ASC";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                contents.add(new Content(Integer.parseInt(database.rs.getString("content.ContentId")),
                        database.rs.getString("content.film"), Float.parseFloat(database.rs.getString("seen.Percentage"))));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void getAllSeen() {
        try {
            String theQuery = "SELECT  content.ContentID, ProfileNumber\n"
                    + "FROM seen\n"
                    + "JOIN content\n"
                    + "ON Content.contentID = Seen.ContentID\n"
                    + "WHERE TV_show IS NULL AND Percentage = 100.0";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }

            while (database.rs.next()) {
                seens.add(new Seen(Integer.parseInt(database.rs.getString("ContentId")),
                        Integer.parseInt(database.rs.getString("ProfileNumber"))));
            }

        } catch (Exception ex) {
            System.out.println("error: " + ex);
        }
    }

    public void countViews() {
        int count = 0;

        for (int i = 0; i < seens.size(); i++) {
            if (seens.get(i).getContentId() == selectedContent.getContentID()) {
                count++;
            }
        }
        nrOfUsers.setText(count + "");
    }
}
