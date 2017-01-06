package netflixstatistics;

// @author Bart
import domain.Account;
import domain.Profile;
import domain.Content;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class CreateSeenPanel extends JPanel {

    private Content selectedContent;
    private List<Content> contents = new ArrayList<Content>();
    private Profile selectedProfile;
    private List<Profile> profiles = new ArrayList<Profile>();
    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();

    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField idField, percentageField;
    private JLabel id, subNumber, name, program, percentage;
    private NSButton cancel, confirm, info;
    private JComboBox subNumberBox, nameBox, programBox;

    private DBConnect database;

    private JPanel thisPanel;

    public CreateSeenPanel() {
        database = new DBConnect();
        thisPanel = this;

        //Setting layout for hole panel
        setLayout(new BorderLayout());

        //Banner
        //
        banner = new BannerPanel();

        //Setting dimension for banner
        banner.setPreferredSize(new Dimension(600, 150));

        //Menu
        //
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
        menuConfigBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons & labels
        menuConfigBtn.setForeground(Color.BLACK);//Is black because active

        //Adding buttons in menupanel
        menu.add(menuAccBtn);
        menu.add(menuFilmBtn);
        menu.add(menuShowBtn);
        menu.add(menuExtraBtn);
        menu.add(menuConfigBtn);
        menu.setLayout(new GridLayout(5, 1));

        //Content
        //
        content = new JPanel();
        content.setSize(600, 250);
        content.setBackground(Color.BLACK);

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        content.setBorder(border);

        //setting GridLayout 
        content.setLayout(new GridLayout(7, 2, 20, 10));

        //Initializing labels
        id = new JLabel("ID: ");
        subNumber = new JLabel("Subscriber number | Subscriber name: ");
        name = new JLabel("Subscriber number | Profile name: ");
        program = new JLabel("Program: ");
        percentage = new JLabel("Percentage: ");

        //Setting text white
        id.setForeground(Color.WHITE);
        subNumber.setForeground(Color.WHITE);
        program.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        percentage.setForeground(Color.WHITE);

        //Initializing combobox
        subNumberBox = new JComboBox();
        nameBox = new JComboBox();
        programBox = new JComboBox();

        //Initializing textfields
        idField = new JTextField(20);
        getNewID();
        idField.setEditable(false);
        percentageField = new JTextField(20);

        //Initializing buttons
        info = new NSButton("Info");
        cancel = new NSButton("Cancel");
        confirm = new NSButton("Create seen");

        InfoBtnHandler infoBtnHandler = new InfoBtnHandler();
        info.addActionListener(infoBtnHandler);
        CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
        cancel.addActionListener(cancelBtnHandler);
        ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
        confirm.addActionListener(confirmBtnHandler);

        getAllAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            subNumberBox.addItem(accounts.get(i).getSubscriberNumber()
                    + "    |    " + accounts.get(i).getName());
        }
        getAllProfiles();
        for (int i = 0; i < profiles.size(); i++) {
            nameBox.addItem(profiles.get(i).getSubscriberNumber()
                    + "    |    " + profiles.get(i).getName());
        }
        getAllContent();
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).getFilm() != null) {
                programBox.addItem(contents.get(i).getFilm());
            } else {
                programBox.addItem(contents.get(i).getTvShow() + " " + contents.get(i).getSeasonCode());
            }
        }

        //Adding buttons in contentpanel
        content.add(id);
        content.add(idField);
        content.add(subNumber);
        content.add(subNumberBox);
        content.add(name);
        content.add(nameBox);
        content.add(program);
        content.add(programBox);
        content.add(percentage);
        content.add(percentageField);
        content.add(info);
        content.add(new JLabel(""));
        content.add(cancel);
        content.add(confirm);

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
                        
        class MenuConfigBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    //Content Handler
    class CancelBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    class InfoBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(thisPanel, "The subscriber number should be the same in both "
                    + "comboboxes in order to create a 'seen'",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Database handlers
    public void getNewID() {
        try {
            String theQuery = "SELECT MAX(SeenID) FROM `seen`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {

                if (database.rs.getString("MAX(SeenID)") != null) {
                    int i = Integer.parseInt(database.rs.getString("MAX(SeenID)"));
                    i += 1;
                    idField.setText("" + i);
                } else {
                    idField.setText("1");
                }
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
                accounts.add(new Account(Integer.parseInt(database.rs.getString("SubscriberNumber")),
                        database.rs.getString("Name"), database.rs.getString("Street"),
                        database.rs.getString("PostalCode"), Integer.parseInt(database.rs.getString("StreetNumber")),
                        database.rs.getString("City"), database.rs.getString("Birthday")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
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

    class ConfirmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int subNmbr = 0;
            int profileNmbr = 0;
            int contentID = 0;

            for (int i = 0; i < accounts.size(); i++) {
                if (subNumberBox.getSelectedItem().toString().endsWith(accounts.get(i).getName())) {
                    subNmbr = accounts.get(i).getSubscriberNumber();
                }

                for (int j = 0; j < profiles.size(); j++) {
                    if (nameBox.getSelectedItem().toString().endsWith(profiles.get(j).getName())) {
                        profileNmbr = profiles.get(j).getProfileNumber();
                    }
                    for (int k = 0; k < contents.size(); k++) {
                        if (programBox.getSelectedItem().toString().equals(contents.get(k).getFilm())) {
                            contentID = contents.get(k).getContentID();
                        }
                        if (programBox.getSelectedItem().toString().equals(contents.get(k).getTvShow()
                                + " " + contents.get(k).getSeasonCode())) {
                            contentID = contents.get(k).getContentID();
                        }
                    }
                }
            }
            if (!percentageField.getText().trim().equals("")) {
                if (subNumberBox.getSelectedItem().toString().regionMatches(0, nameBox.getSelectedItem().toString(), 0, 4)) {
                    System.out.println("Created new seen");
                    database.createData("Seen", "SeenID ,ProfileNumber, SubscriberNumber, ContentID, Percentage",
                            "'" + idField.getText() + "','" + profileNmbr + "','"
                            + subNmbr + "','" + contentID + "','" + percentageField.getText() + "'");
                    new ConfigGUI();
                    SwingUtilities.windowForComponent(thisPanel).dispose();
                } else {
                    JOptionPane.showMessageDialog(thisPanel, "Subscriber numbers are not the same",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Please fill in all the required fields.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}
