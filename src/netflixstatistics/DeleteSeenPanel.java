package netflixstatistics;

// @AUTHOR Felix

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;


public class DeleteSeenPanel extends JPanel {
    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField percentageField;
    private JLabel id, subNumber, name, program, percentage; 
    private NSButton cancel, confirm; 
    private JComboBox idBox, subNumberBox, nameBox, programBox;
    
    private JPanel thisPanel;
            
    public DeleteSeenPanel()
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
            content.setLayout(new GridLayout(6,2,20,12));
            
            //Initializing labels
            id = new JLabel("ID: ");
            subNumber = new JLabel("Subscriber number: ");
            name = new JLabel("Name: ");
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
            subNumberBox = new JComboBox();
            nameBox = new JComboBox();
            programBox = new JComboBox();
            
            //Initializing textfields
            percentageField = new JTextField(20);
            
            //Initializing buttons
            cancel = new NSButton("Cancel");
            confirm = new NSButton("Delete seen");
     
            CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
            cancel.addActionListener(cancelBtnHandler);
            
            //Adding buttons in contentpanel
            content.add(id);
            content.add(idBox);
            content.add(subNumber);
            content.add(subNumberBox);
            content.add(name);
            content.add(nameBox);
            content.add(program);
            content.add(programBox); 
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

