package netflixstatistics;

// @AUTHOR Felix

import javax.swing.*;

 

public class EditAccGUI extends JFrame {
    public EditAccGUI() {
        ImageIcon img = new ImageIcon("src/image/icon.png");
        
        setIconImage(img.getImage());
        setTitle("Netflix Statistix");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new EditAccPanel());
        setResizable(false);
        setVisible(true);
    }
}
