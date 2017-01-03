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

 
class AccountPanel extends JPanel {
    private Account selectedAccount;
    private List<Account> accounts = new ArrayList<Account>();
    private Profile selectedProfile;
    private List<Profile> profiles = new ArrayList<Profile>();
    
    private final JPanel menu, content;
    private final BannerPanel banner;
    private final JLabel info;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private final NSButton contentFilmBtn, contentShowBtn;
    private JComboBox contentAccountBox;
    
    private DBConnect database;
    
    private JPanel thisPanel;
            
    public AccountPanel() 
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
            menuAccBtn.setBackground(Color.WHITE);//Is white because active
       
            //Setting text color for buttons
            menuAccBtn.setForeground(Color.BLACK);//Is black because active
        
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
            
            //setting layout null so setBounds works
            content.setLayout(null);
            
            //Initializing buttons, label and combobox
            contentFilmBtn = new NSButton("Film");
            contentShowBtn = new NSButton("Show");
            contentAccountBox = new JComboBox();
            info = new JLabel("Profile Number | Subscriber Number | Name profile");
            
            DropDownHandler dropDownHandler = new DropDownHandler();
            contentAccountBox.addActionListener(dropDownHandler);
            
            //Setting color info white
            info.setForeground(Color.WHITE);
            
            //Adding handlers to buttons
            ShowBtnHandler showBtnHandler = new ShowBtnHandler();
            contentShowBtn.addActionListener(showBtnHandler);
            FilmBtnHandler filmBtnHandler = new FilmBtnHandler();
            contentFilmBtn.addActionListener(filmBtnHandler);
           
            //Setting location of buttons, label and combobox
            contentAccountBox.setBounds(50,30,400,25);
            info.setBounds(50, 10, 400, 25);
            contentFilmBtn.setBounds(50,70,200,100);
            contentShowBtn.setBounds(250,70,200,100);   
            
            getAllAccounts();
            getAllProfiles();
            for (int i = 0; i < profiles.size(); i++) {
                contentAccountBox.addItem(profiles.get(i).getProfileNumber() + " | " + profiles.get(i).getSubscriberNumber()+ " | " + 
                                profiles.get(i).getName());
              
            }
            
            //Adding buttons in contentpanel
            content.add(info);
            content.add(contentAccountBox);
            content.add(contentFilmBtn);
            content.add(contentShowBtn);
            
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
    }class MenuFilmBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new FilmGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }class MenuShowBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new ShowGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        
    //Handlers for content buttons    
        class DropDownHandler implements ActionListener
        {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        for (int i = 0; i < profiles.size(); i++){
            if (contentAccountBox.getSelectedItem().toString().startsWith(profiles.get(i).getProfileNumber()+"")){
                        selectedProfile = profiles.get(i);
                    }
            }
        }
    }
    
        class ShowBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new ShowAccGUI(selectedProfile);
            SwingUtilities.windowForComponent(thisPanel).dispose();
        } 
    } 
        
        class FilmBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new FilmAccGUI(selectedProfile);
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
        
        //Database Handlers 
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
}
