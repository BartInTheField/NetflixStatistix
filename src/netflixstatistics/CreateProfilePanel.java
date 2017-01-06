package netflixstatistics;

// @author Bart
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
import domain.Account;

public class CreateProfilePanel extends JPanel {

    private DBConnect database;

    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();

    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField nameField, birthdayField, profileField;
    private JLabel subNumber, name, birthday, profile;
    private NSButton cancel, confirm;
    private JComboBox subNumberBox;

    private JPanel thisPanel;

    public CreateProfilePanel() {
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
        profile = new JLabel("Profile number: ");
        subNumber = new JLabel("Subscriber name: ");
        name = new JLabel("Name: ");
        birthday = new JLabel("Birthday: ");

        //Setting text white
        profile.setForeground(Color.WHITE);
        subNumber.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        birthday.setForeground(Color.WHITE);

        //Initializing combobox
        subNumberBox = new JComboBox();

        //Initializing textfields
        profileField = new JTextField(20);
        profileField.setEditable(false);
        getNewProfileID();
        nameField = new JTextField(20);
        birthdayField = new JTextField(20);

        //Setting text for default date 
        birthdayField.setText("DD/MM/YYYY");

        //Initializing buttons
        cancel = new NSButton("Cancel");
        confirm = new NSButton("Create profile");

        ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
        confirm.addActionListener(confirmBtnHandler);
        CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
        cancel.addActionListener(cancelBtnHandler);

        //Filling combobox with subnumbers
        getAllAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            subNumberBox.addItem(accounts.get(i).getName());
        }

        //Adding buttons in contentpanel
        content.add(profile);
        content.add(profileField);
        content.add(subNumber);
        content.add(subNumberBox);
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

    //Content Button Handlers
    class CancelBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

    //Databases
    public void getNewProfileID() {
        try {
            String theQuery = "SELECT MAX(ProfileNumber) FROM `profile`";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {

                if (database.rs.getString("MAX(ProfileNumber)") != null) {
                    int i = Integer.parseInt(database.rs.getString("MAX(ProfileNumber)"));
                    i += 1;
                    profileField.setText("" + i);
                } else {
                    profileField.setText("1");
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
                accounts.add(new Account(Integer.parseInt(database.rs.getString("SubscriberNumber")), database.rs.getString("Name"), database.rs.getString("Street"), database.rs.getString("PostalCode"), Integer.parseInt(database.rs.getString("StreetNumber")),
                        database.rs.getString("City"), database.rs.getString("Birthday")));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    class ConfirmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!nameField.getText().trim().equals("")) {
                int subNmbr = 0;
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getName().equals(subNumberBox.getSelectedItem().toString())) {
                        subNmbr = accounts.get(i).getSubscriberNumber();
                    }
                }
                System.out.println("Created new profile");
                database.createData("profile", "ProfileNumber, SubscriberNumber, Name, Birthday",
                        "'" + profileField.getText() + "','" + subNmbr + "','"
                        + nameField.getText() + "','" + birthdayField.getText() + "'");
                new ConfigGUI();
                SwingUtilities.windowForComponent(thisPanel).dispose();
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Please fill in all the required fields.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
