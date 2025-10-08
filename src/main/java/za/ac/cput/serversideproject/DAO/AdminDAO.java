
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
