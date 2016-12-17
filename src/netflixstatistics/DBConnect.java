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
    
       public void createData(String table, String attributes, String value)
    {
        try{
            String query = "INSERT INTO "+table+" ("+attributes+") VALUES ("+value+")";
            st.executeUpdate(query);
        }catch(Exception ex){
            System.out.println("Error: " +ex);
        }
    }
//       public void editData (String table, String attributes, String newValue, int rowcount)
//       {
//           try {
//               for(int i = 0; i < rowcount; i++) {
//                   String query = "UPDATE "+table+" SET "+attributes+" = "+newValue;
//               }
//           }
//           catch (Exception ex){
//               System.out.println("Error: " + ex);
//           }
//       }
}
