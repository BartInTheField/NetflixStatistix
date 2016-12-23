package netflixstatistics;

// @AUTHOR Felix

import domain.Profile;
import javax.swing.*;

 

public class ShowAccGUI extends JFrame {
    public ShowAccGUI(Profile profile) {
        ImageIcon img = new ImageIcon("src/image/icon.png");
        
        setIconImage(img.getImage());
        setTitle("Netflix Statistix");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ShowAccPanel(profile));
        setResizable(false);
        setVisible(true);
    }
}
