package netflixstatistics;
import java.sql.*;

/**
 *
 * @author Bart
 */
public class DBConnect {
    
    
    private Connection con;
    public Statement st;
    public ResultSet rs;
    
    
    
    public DBConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/netflix_statistix", "root", "");
            st = con.createStatement();
            
        }catch  (Exception ex){
            System.out.println("Error: " + ex);
        }
    }
}
