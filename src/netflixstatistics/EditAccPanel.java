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


public class EditAccPanel extends JPanel {
    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField nameField, addressField, cityField; 
    private JLabel subNumber, name, address, city; 
    private NSButton cancel, confirm; 
    private JComboBox subNumberBox;
    
    private JPanel thisPanel;
            
    public EditAccPanel()
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
            content.setLayout(new GridLayout(5,2,20,20));
            
            //Initializing labels
            subNumber = new JLabel("Subscribernumber: ");
            name = new JLabel("Name: ");
            address = new JLabel("Address: ");
            city = new JLabel("City: ");
            
            //Setting text white
            subNumber.setForeground(Color.WHITE);
            name.setForeground(Color.WHITE);
            address.setForeground(Color.WHITE);
            city.setForeground(Color.WHITE);
            
            //Initializing combobox
            subNumberBox = new JComboBox();
            
            //Initializing textfields
            nameField = new JTextField(20);
            addressField = new JTextField(20);
            cityField = new JTextField(20);
            
            
            
            //Initializing buttons
            cancel = new NSButton("Cancel");
            confirm = new NSButton("Edit account");
     
            CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
            cancel.addActionListener(cancelBtnHandler);
            
            //Adding buttons in contentpanel
            content.add(subNumber);
            content.add(subNumberBox);
            content.add(name);
            content.add(nameField); 
            content.add(address);
            content.add(addressField);
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
        
        class CancelBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new ConfigGUI();
            SwingUtilities.windowForComponent(thisPanel).dispose();
        }
    }
}


