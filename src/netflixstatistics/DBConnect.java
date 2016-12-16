package netflixstatistics;
import java.sql.*;

/**
 *
 * @author Bart
 */
public class DBConnect {
    
   
    public int rowcount = 0;
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
    
       public void createData(String Table, String Attributes, String Value)
    {
        try{
            String query = "INSERT INTO "+Table+" ("+Attributes+") VALUES ("+Value+")";
            st.executeUpdate(query);
        }catch(Exception ex){
            System.out.println("Error: " +ex);
        }
    }
}
