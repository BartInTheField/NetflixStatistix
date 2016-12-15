package netflixstatistics;

// @AUTHOR Felix

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

 
public class BannerPanel extends JPanel {
       
    public BannerPanel() {
        
    }
    
     public Image getBannerImage() {
        ImageIcon ic = new ImageIcon("src/image/banner.png");
        return ic.getImage();
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(getBannerImage(), 0, 0, null);
    }
}
