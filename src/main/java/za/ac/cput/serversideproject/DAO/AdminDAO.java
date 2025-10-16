
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.shared.WorkerClasses.Admin;

/**
 * ADP Final assignment
 */

public class AdminDAO {
  private Connection conn;

public AdminDAO(Connection conn) {
  this.conn = conn;  
}

public boolean validateAdmin(String adminId, String password) throws SQLException {
  String sql = "SELECT * FROM ADMIN WHERE adminId = ? AND password = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, adminId);
ps.setString(2, password);
ResultSet rs = ps.executeQuery();

return rs.next();
}

public Admin findAdmin(String username) throws SQLException {
 String query = "SELECT * FROM ADMIN WHERE ADMINID = ?";
 PreparedStatement ps = conn.prepareStatement(query);
 ps.setString(1, username);
 ResultSet rs = ps.executeQuery();
 if (rs.next()) {
     return new Admin(
             rs.getString("adminId"),
             rs.getString("password"),
             rs.getString("name"),
             rs.getString("surname"),
             rs.getString("email"),
             rs.getString("contactNo"),
             rs.getString("role")
     );
 }
 return null;
}

public List<Admin> getAllAdmins() throws SQLException {
    List<Admin> admins = new ArrayList<>();
    String query = "SELECT * FROM ADMIN"; 

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Admin admin = new Admin(
                rs.getString("ADMINID"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getString("SURNAME"),
                rs.getString("EMAIL"),
                rs.getString("CONTACTNO"),
                rs.getString("ROLE")
            );
            admins.add(admin);
        }
    }
    return admins;
}
}
