package org.prac.project.basic;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTests {
	
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=projectDB;"
                        + "user=sa;"
                        + "password=1234;";
 
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_member");
 
              while (rs.next()) {
            	  
            	  String result = rs.toString();
                  
                  System.out.println(result);                  
              }           
            rs.close();
            stmt.close();
            connection.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
