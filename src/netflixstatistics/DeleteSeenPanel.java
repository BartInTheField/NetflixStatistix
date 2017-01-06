package netflixstatistics;

// @AUTHOR Felix

import domain.Account;
import domain.Content;
import domain.Profile;
import domain.Seen;
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


public class DeleteSeenPanel extends JPanel {
    private DBConnect database;
    
    private Seen selectedSeen;
    private List<Seen> seens = new ArrayList<Seen>(); 
    private Content selectedContent;
    private List<Content> contents = new ArrayList<Content>();
    private Profile selectedProfile;
    private List<Profile> profiles = new ArrayList<Profile>();
    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();
    
    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField percentageField, nameField, programField, subNumberField;
    private JLabel id, subNumber, name, program, percentage; 
    private NSButton cancel, confirm; 
    private JComboBox idBox;
    
    private JPanel thisPanel;
            
    public DeleteSeenPanel()
    {
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
            menuConfigBtn= new NSButton("Config");
            
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
            menu.setLayout(new GridLayout(5,1));
        
        //content
        content = new JPanel();
        content.setSize(600, 250);
        content.setBackground(Color.BLACK);
        
        Border border = BorderFactory.createEmptyBorder(10,10,10,10);
        content.setBorder(border);
            
            //setting GridLayout 
            content.setLayout(new GridLayout(6,2,20,12));
            
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
            idBox = new JComboBox();
            
            DropDownHandler dropDownHandler = new DropDownHandler();
            idBox.addActionListener(dropDownHandler);
            
            //Initializing textfields
            percentageField = new JTextField(20);
            subNumberField = new JTextField(20);
            nameField = new JTextField(20);
            programField = new JTextField(20);
            
            //Setting editable false
            subNumberField.setEditable(false);
            nameField.setEditable(false);
            programField.setEditable(false);
            percentageField.setEditable(false);
            
            //Initializing buttons
            cancel = new NSButton("Cancel");
            confirm = new NSButton("Delete seen");
     
            CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
            cancel.addActionListener(cancelBtnHandler);
            ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
            confirm.addActionListener(confirmBtnHandler);
            
            getAllProfiles();
            getAllAccounts();
            getAllContent();
            getAllSeen();
            for (int i = 0; i < seens.size(); i++) {
            idBox.addItem(seens.get(i).getSeenId());
        }
            
            
            //Adding buttons in contentpanel
            content.add(id);
            content.add(idBox);
            content.add(subNumber);
            content.add(subNumberField);
            content.add(name);
            content.add(nameField);
            content.add(program);
            content.add(programField); 
            content.add(percentage);
            content.add(percentageField);
            content.add(cancel);
            content.add(confirm);
            
        //Adding panels to Accountpanel
        add(banner, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(menu, BorderLayout.WEST);
    }
    //Handlers for menu buttons
         class MenuAccBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new GUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }

        class MenuFilmBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new FilmGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        
        class MenuShowBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
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
        class CancelBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        
        //Database handlers
        
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
         
          public void getSeenInfo(String seenID) {
        try {
            String theQuery = "SELECT Percentage FROM `seen` WHERE SeenID='"+seenID+"'";
            database.rs = database.st.executeQuery(theQuery);
            if (database.rs.last()) {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while (database.rs.next()) {
                percentageField.setText(database.rs.getString("Percentage"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    class DropDownHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < seens.size(); i++) {
                if (idBox.getSelectedItem().toString().equals(seens.get(i).getSeenId() + "")) {
                    selectedSeen = seens.get(i);
                    break;
                }
            }
            getSeenInfo(idBox.getSelectedItem().toString());
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getSubscriberNumber() == (selectedSeen.getSubscriberNumber())) {
                    subNumberField.setText(accounts.get(i).getSubscriberNumber() 
                            + " | " + accounts.get(i).getName());
                }
            }
            for (int i = 0; i < profiles.size(); i++) {
                if (profiles.get(i).getProfileNumber() == (selectedSeen.getProfileNumber())) {
                    nameField.setText(profiles.get(i).getSubscriberNumber() 
                            + " | " + profiles.get(i).getName());
                }
            }
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).getContentID() == (selectedSeen.getContentId())) {
                    if (contents.get(i).getFilm() != null){
                    programField.setText(contents.get(i).getFilm());
                    }
                    else{
                    programField.setText(contents.get(i).getTvShow() + " " + 
                            contents.get(i).getSeasonCode());
                    }
                }
            }
        }
    }
    
       class ConfirmBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            database.DeleteInfo("seen", "SeenID", selectedSeen.getSeenId()+"");
            System.out.println("Deleted seen");
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
}



