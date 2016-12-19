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
       
       public void updateAccInfo(String accID, String name, String street, 
               String postalCode , String streetNumber, String city, String birthday)
    {
        try{
            String theQuery = "UPDATE `account` SET Name='"+name+"', Street='"+street+"', "
                    + "PostalCode ='"+postalCode+"', StreetNumber='"+streetNumber+"', "
                    + "City='"+city+"', Birthday='"+birthday+"' WHERE SubscriberNumber='"+accID+"'";
            st.executeUpdate(theQuery);
            
        }catch(Exception ex){
            System.out.println("Error: " +ex);
        }
    }
       
       public void updateProfileInfo(String profileID, String name, String birthday)
    {
        try{
            String theQuery = "UPDATE `profile` SET Name='"+name+"', Birthday ='"+birthday+"' "
                    + "WHERE ProfileNumber='"+profileID+"'";
            st.executeUpdate(theQuery);
            
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
