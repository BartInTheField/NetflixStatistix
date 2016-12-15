package netflixstatistics;

// @author Bart

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


public class NSButton extends JButton{
    
    private static String OS = System.getProperty("os.name").toLowerCase();

    public NSButton(String label)
    {
        super(label);
        
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setOpaque(true);
        
        if (isWindows()) 
        {
		
                setBorderPainted(true);
                setBackground(Color.BLACK);
        } 
        else if (isMac()) 
        {
                setBorderPainted(false);
                setBackground(Color.DARK_GRAY);
        }
        
        
    }
    
    public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}
}

