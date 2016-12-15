package netflixstatistics;

// @AUTHOR Felix

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

 
class FilmPanel extends JPanel {
    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JComboBox contentAccountBox;
    private JLabel nrOfUsersLb, nrOfUsers;
    
    private JPanel thisPanel;
            
    public FilmPanel() 
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
            MenuExtraBtnHandler extraBtnHandler = new MenuExtraBtnHandler();
            menuExtraBtn.addActionListener(extraBtnHandler);
            MenuConfigBtnHandler configBtnHandler = new MenuConfigBtnHandler();
            menuConfigBtn.addActionListener(configBtnHandler);
            
            //Initializing labels
            nrOfUsersLb = new JLabel("How many users watched this movie 100%?");
            nrOfUsers = new JLabel("1337");
        
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
            menu.setLayout(new GridLayout(5,1));
        
        //content
        content = new JPanel();
        content.setSize(600, 250);
        content.setBackground(Color.BLACK);
            
            //setting layout null so setBounds works
            content.setLayout(null);
            
            //Initializing buttons and combobox
            contentAccountBox = new JComboBox();
            
            //Setting location of buttons
            contentAccountBox.setBounds(50,30,400,25);
            
            //Setting location of labels
            nrOfUsersLb.setBounds(50, 60, 400, 25);
            nrOfUsers.setBounds(370, 60, 80, 25);
            nrOfUsers.setHorizontalAlignment(SwingConstants.RIGHT);
     
            //Adding buttons in contentpanel
            content.add(contentAccountBox);
            content.add(nrOfUsersLb);
            content.add(nrOfUsers);
        
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
}
