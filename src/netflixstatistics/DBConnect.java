package netflixstatistics;

// @author Bart

import java.sql.*;


public class DBConnect {

    private Connection con;
    public Statement st;
    public ResultSet rs;
    
    
    
    public DBConnect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/netflix_statistx", "root", "");
            st = con.createStatement();
            
        }catch  (ClassNotFoundException | SQLException ex){
            System.out.println("Error: No connection found");
        }
    }
}

