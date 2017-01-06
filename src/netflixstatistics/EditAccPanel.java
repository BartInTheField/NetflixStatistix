package netflixstatistics;

// @author Bart
import domain.Account;
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

public class EditAccPanel extends JPanel {

    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();

    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField nameField, streetField, postalCodeField, streetNumberField, cityField, birthdayField;
    private JLabel subNumber, name, street, postalCode, streetNumber, city, birthday;
    private NSButton cancel, confirm;
    private JComboBox subNumberBox;

    private DBConnect database;
    private JPanel thisPanel;

    public EditAccPanel() {
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
        content.setLayout(new GridLayout(7, 2, 20, 6));

        //Initializing labels
        subNumber = new JLabel("Subscriber number: ");
        name = new JLabel("Name: ");
        street = new JLabel("Street: ");
        postalCode = new JLabel("Postal code: ");
        streetNumber = new JLabel("Street number: ");
        city = new JLabel("City: ");
        birthday = new JLabel("Birthday: ");

        //Setting text white
        subNumber.setForeground(Color.WHITE);
        name.setForeground(Color.WHITE);
        street.setForeground(Color.WHITE);
        postalCode.setForeground(Color.WHITE);
        streetNumber.setForeground(Color.WHITE);
        city.setForeground(Color.WHITE);
        birthday.setForeground(Color.WHITE);

        //Initializing combobox
        subNumberBox = new JComboBox();

        DropDownHandler dropDownHandler = new DropDownHandler();
        subNumberBox.addActionListener(dropDownHandler);

        //Initializing textfields
        nameField = new JTextField(20);
        streetField = new JTextField(20);
        postalCodeField = new JTextField(20);
        streetNumberField = new JTextField(20);
        cityField = new JTextField(20);
        birthdayField = new JTextField(20);

        //Initializing buttons
        cancel = new NSButton("Cancel");
        confirm = new NSButton("Edit account");

        CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
        cancel.addActionListener(cancelBtnHandler);
        ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
        confirm.addActionListener(confirmBtnHandler);

        getAllAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            subNumberBox.addItem(accounts.get(i).getSubscriberNumber());
        }

        //Adding buttons in contentpanel
        content.add(subNumber);
        content.add(subNumberBox);
        content.add(name);
        content.add(nameField);
        content.add(street);
        content.add(streetField);
        content.add(postalCode);
        content.add(postalCodeField);
        content.add(streetNumber);
        content.add(streetNumberField);
        content.add(city);
        content.add(cityField);
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

    public void getAccInfo(String accID) {
        try {
            String theQuery = "SELECT Name, Street, PostalCode, StreetNumber, "
                    + "City, Birthday FROM `account` WHERE SubscriberNumber='" + accID + "'";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                nameField.setText(database.rs.getString("Name"));
                streetField.setText(database.rs.getString("Street"));
                postalCodeField.setText(database.rs.getString("PostalCode"));
                streetNumberField.setText(database.rs.getString("StreetNumber"));
                cityField.setText(database.rs.getString("City"));
                birthdayField.setText(database.rs.getString("Birthday"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    class DropDownHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < accounts.size(); i++) {
                if (subNumberBox.getSelectedItem().toString().equals(accounts.get(i).getSubscriberNumber() + "")) {
                    selectedAccount = accounts.get(i);
                    break;
                }
            }
            getAccInfo(subNumberBox.getSelectedItem().toString());
        }
    }

    class ConfirmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!nameField.getText().trim().equals("") && !streetField.getText().trim().equals("")
                    && !postalCodeField.getText().trim().equals("") && !streetField.getText().trim().equals("")
                    && !cityField.getText().trim().equals("") && !birthdayField.getText().trim().equals("")) {
                database.updateAccInfo(selectedAccount.getSubscriberNumber() + "", nameField.getText(),
                        streetField.getText(), postalCodeField.getText(),
                        streetNumberField.getText(), cityField.getText(), birthdayField.getText());
                System.out.println("Updated Account");
                new ConfigGUI();
                SwingUtilities.windowForComponent(thisPanel).dispose();
            } else {
                JOptionPane.showMessageDialog(thisPanel, "Please fill in all the required fields.",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
