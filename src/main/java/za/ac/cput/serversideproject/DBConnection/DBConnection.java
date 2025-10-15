
package za.ac.cput.serversideproject.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ADP Final assignment
 */

public class DBConnection {
   public static Connection getConnection() throws SQLException {
   String DATABASE_URL = "jdbc:derby://localhost:1527/StudentEnrollmentDB";
   String username = "administrator";
   String password = "admin";

   Connection connection = DriverManager.getConnection(DATABASE_URL, username, password);
   return connection;    
   } 
}
