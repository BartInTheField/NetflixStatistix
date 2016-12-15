package netflixstatistics;

// @AUTHOR Felix

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

 
class FilmAccPanel extends JPanel {
    private final JPanel menu, content;
    private final BannerPanel banner;
    private final NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JLabel profileNameLb, filmsWatchedLb;
    private JTextArea films;
    private JScrollPane scrollPane;
    
    private JPanel thisPanel;
            
    public FilmAccPanel() 
    {
        thisPanel = this;
        
        //Setting layout for whole panel
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
            profileNameLb = new JLabel("Jesse Witje");
            filmsWatchedLb = new JLabel("Films Watched:");
            
            //Initializing textarea & scrollpanel
            films = new JTextArea();
            scrollPane = new JScrollPane(films);
        
            //Setting background color for buttons
            menuAccBtn.setBackground(Color.WHITE);//Is white because active
       
            //Setting text color for buttons & labels
            menuAccBtn.setForeground(Color.BLACK);//Is black because active
            profileNameLb.setForeground(Color.WHITE);
            filmsWatchedLb.setForeground(Color.WHITE);
        
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
            
            //Setting location of labels & textarea
            profileNameLb.setBounds(50, 30, 400, 25);
            filmsWatchedLb.setBounds(50, 55, 400, 25);
//            films.setBounds(50, 80, 400, 130);
            scrollPane.setBounds(50, 80, 400, 130);
            profileNameLb.setHorizontalAlignment(SwingConstants.CENTER);
            filmsWatchedLb.setHorizontalAlignment(SwingConstants.CENTER);
            
            //Setting other properties
            profileNameLb.setFont(new Font("", Font.BOLD, 20));
            films.setMargin(new Insets(4, 6, 4, 6));
            scrollPane.setBackground(Color.WHITE);

            films.setLineWrap(true);
            films.setWrapStyleWord(true);
            
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     
            //Adding buttons & textarea in contentpanel
            content.add(profileNameLb);
            content.add(filmsWatchedLb);
            content.add(scrollPane);
        
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
