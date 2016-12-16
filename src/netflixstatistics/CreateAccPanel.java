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


public class CreateAccPanel extends JPanel {
    private JPanel menu, content;
    private BannerPanel banner;
    private NSButton menuAccBtn, menuFilmBtn, menuShowBtn, menuExtraBtn, menuConfigBtn;
    private JTextField subNumberField, nameField, streetField, postalCodeField, streetNumberField, cityField ,birthdayField; 
    private JLabel subNumber, name, street, postalCode, streetNumber, city, birthday; 
    private NSButton cancel, confirm; 
    
    private DBConnect database;
    
    private JPanel thisPanel;
            
    public CreateAccPanel()
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
            content.setLayout(new GridLayout(8,2,20,6));
            
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
            
            //Initializing textfields
            subNumberField = new JTextField(20);
            subNumberField.setEditable(false);
            getNewAccID();
            nameField = new JTextField(20);
            streetField = new JTextField(20);
            postalCodeField = new JTextField(20);
            streetNumberField = new JTextField(20);
            cityField = new JTextField(20);
            birthdayField = new JTextField(20);
            
            //Initializing buttons
            cancel = new NSButton("Cancel");
            confirm = new NSButton("Create account");
     
            ConfirmBtnHandler confirmBtnHandler = new ConfirmBtnHandler();
            confirm.addActionListener(confirmBtnHandler);
            CancelBtnHandler cancelBtnHandler = new CancelBtnHandler();
            cancel.addActionListener(cancelBtnHandler);
            
            //Adding buttons in contentpanel
            content.add(subNumber);
            content.add(subNumberField);
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
        
        //Database
        public void getNewAccID()
    {
        try{
            String theQuery = "SELECT MAX(SubscriberNumber) FROM `account`";
            database.rs = database.st.executeQuery(theQuery);
            if(database.rs.last())
            {
                database.rowcount = database.rs.getRow();
                database.rs.beforeFirst();
            }
            while(database.rs.next()){
                
                if(database.rs.getString("MAX(SubscriberNumber)") != null)
                {
                    int i = Integer.parseInt(database.rs.getString("MAX(SubscriberNumber)"));
                    i +=1;
                    subNumberField.setText(""+i);
                }
                else
                {
                    subNumberField.setText("1");
                }
            }
        }catch(Exception ex){
            System.out.println("Error: " +ex);
        }
    }
        
        class ConfirmBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(!nameField.getText().equals(""))
            {
                System.out.println("Create new account");
                database.createData("account", "SubscriberNumber, Name, Street, PostalCode, StreetNumber, City, Birthday", 
                                "'"+ subNumberField.getText() + "','"+nameField.getText() 
                                + "','" + streetField.getText()+"','" + 
                                postalCodeField.getText()+"','" + streetNumberField.getText()
                                +"','" + cityField.getText()+"','" + birthdayField.getText()+"'");
                new ConfigGUI();
                SwingUtilities.windowForComponent(thisPanel).dispose();
            }
        }
    }
}


