package netflixstatistics;

// @author Bart
import domain.Account;
import domain.Profile;
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

public class DeleteProfilePanel extends JPanel {

    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();
    private Profile selectedProfile;
    private List<Profile> profiles = new ArrayList<Profile>();

    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField profileSubscriberName, nameField, birthdayField;
    private JLabel subNumber, name, birthday, profileSubscriberNameText;
    private NSButton cancel, confirm;
    private JComboBox subNumberBox;

    private JPanel thisPanel;
    private DBConnect database;

    public DeleteProfilePanel() {
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
        menuConfigBtn.setBackground(Color.WHITE);//Is white because active

        //Setting text color for buttons & labels
        menuConfigBtn.setForeground(Color.BLACK);//Is black because active

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

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        content.setBorder(border);

        //setting GridLayout 
        content.setLayout(new GridLayout(5, 2, 20, 20));

        //Initializing labels
        subNumber = new JLabel("Profile Number: ");
        name = new JLabel("Profile Name:");
        birthday = new JLabel("Birthday: ");
        profileSubscriberName = new JTextField("");
        profileSubscriberNameText = new JLabel("Subscriber Name: ");

        //Setting text white
        subNumber.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        birthday.setForeground(Color.WHITE);
        profileSubscriberNameText.setForeground(Color.WHITE);

        //Initializing combobox
        subNumberBox = new JComboBox();

        DropDownBtnHandler dropDownBtnHandler = new DropDownBtnHandler();
        subNumberBox.addActionListener(dropDownBtnHandler);

        //Initializing textfields
        nameField = new JTextField(20);
        birthdayField = new JTextField(20);
        
        //Setting editable false
        profileSubscriberName.setEditable(false);
        nameField.setEditable(false);
        birthdayField.setEditable(false);

        //Initializing buttons
        cancel = new NSButton("Cancel");
        confirm = new NSButton("Delete profile");

        CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
        cancel.addActionListener(cancelBtnHandler);
        ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
        confirm.addActionListener(confirmBtnHandler);

        getAllAccounts();
        getAllProfiles();
        for (int i = 0; i < profiles.size(); i++) {
            subNumberBox.addItem(profiles.get(i).getProfileNumber());
        }

        //Adding buttons in contentpanel
        content.add(subNumber);
        content.add(subNumberBox);
        content.add(profileSubscriberNameText);
        content.add(profileSubscriberName);
        content.add(name);
        content.add(nameField);
        content.add(birthday);
        content.add(birthdayField);
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
    
    class MenuExtraBtnHandler implements ActionListener
    {
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

    //Content button handlers 
    class CancelBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    //Database handlers
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

    public void getProfileInfo(String profileID) {
        try {
            String theQuery = "SELECT ProfileNumber, SubscriberNumber, Name, Birthday "
                    + "FROM `profile` WHERE ProfileNumber='" + profileID + "'";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                nameField.setText(database.rs.getString("Name"));
                birthdayField.setText(database.rs.getString("Birthday"));

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    class DropDownBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < profiles.size(); i++) {
                if (subNumberBox.getSelectedItem().toString().equals(profiles.get(i).getProfileNumber() + "")) {
                    selectedProfile = profiles.get(i);
                    break;
                }
            }
            getProfileInfo(subNumberBox.getSelectedItem().toString());
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getSubscriberNumber() == (selectedProfile.getSubscriberNumber())) {
                    profileSubscriberName.setText(accounts.get(i).getName());
                }
            }
        }
    }

    class ConfirmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            database.DeleteInfo("profile", "ProfileNumber", ""+selectedProfile.getProfileNumber());
            System.out.println("Deleted profile");
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
}
