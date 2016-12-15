package netflixstatistics;

// @author Bart

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;


public class ConfigPanel extends JPanel {
    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private NSButton createAcc, createProfile, createWatching,
            editAcc, editProfile, editWatching,
            deleteAcc, deleteProfile, deleteWatching;
    private JLabel account, profile, watching;
    
    private JPanel thisPanel;
            
    public ConfigPanel()
    {
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
            content.setLayout(new GridLayout(4,3,20,20));
            
            //Initializing labels
            account = new JLabel("Account");
            profile = new JLabel("Profile");
            watching = new JLabel("Watching");
            
            //Setting color of labels to white
            account.setForeground(Color.WHITE);
            profile.setForeground(Color.WHITE);
            watching.setForeground(Color.WHITE);
            
            //Setting alignment center
            account.setHorizontalAlignment(SwingConstants.CENTER);
            profile.setHorizontalAlignment(SwingConstants.CENTER);
            watching.setHorizontalAlignment(SwingConstants.CENTER);
            
            //Initializing buttons
            createAcc = new NSButton("Create Account");
            createProfile = new NSButton("Create Profile");
            createWatching = new NSButton("Create Watching");
            
            editAcc = new NSButton("Edit Account");
            editProfile = new NSButton("Edit Profile");
            editWatching = new NSButton("Edit Watching");
            
            deleteAcc = new NSButton("Delete Account");
            deleteProfile = new NSButton("Delete Profile");
            deleteWatching = new NSButton("Delete Watching");

            //Handlers for buttons
            CreateAccBtnHandler createAccBtnHandler = new CreateAccBtnHandler();
            createAcc.addActionListener(createAccBtnHandler);
            CreateProfileBtnHandler createProfileBtnHandler = new CreateProfileBtnHandler();
            createProfile.addActionListener(createProfileBtnHandler);
            CreateWatchingBtnHandler createWatchingBtnHandler = new CreateWatchingBtnHandler();
            createWatching.addActionListener(createWatchingBtnHandler);
            
            EditAccBtnHandler editAccBtnHandler = new EditAccBtnHandler();
            editAcc.addActionListener(editAccBtnHandler);
            EditProfileBtnHandler editProfileBtnHandler = new EditProfileBtnHandler();
            editProfile.addActionListener(editProfileBtnHandler);
            
            DeleteAccBtnHandler deleteAccBtnHandler = new DeleteAccBtnHandler();
            deleteAcc.addActionListener(deleteAccBtnHandler);
            DeleteProfileBtnHandler deleteProfileBtnHandler = new DeleteProfileBtnHandler();
            deleteProfile.addActionListener(deleteProfileBtnHandler);
     
            //Adding buttons in contentpanel
            content.add(account);
            content.add(profile);
            content.add(watching);
            content.add(createAcc);
            content.add(createProfile);
            content.add(createWatching);
            content.add(editAcc); 
            content.add(editProfile);
            content.add(editWatching);
            content.add(deleteAcc);
            content.add(deleteProfile);
            content.add(deleteWatching);
        
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
        
    //Handlers for config create buttons

        class CreateAccBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new CreateAccGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        class CreateProfileBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new CreateProfileGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
              class CreateWatchingBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new CreateWatchingGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        
    //Handlers for config edit buttons
        class EditAccBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new EditAccGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
              class EditProfileBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new EditProfileGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
        
    //Handlers for config delete buttons
              class DeleteAccBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new DeleteAccGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
              class DeleteProfileBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new DeleteProfileGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }            
}
