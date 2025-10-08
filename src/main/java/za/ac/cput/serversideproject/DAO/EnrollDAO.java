
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ADP Final assignment
 */

public class EnrollDAO {
 private Connection conn;
 
 public EnrollDAO(Connection conn) {
     this.conn = conn;
 }
 
 //enrolling a student in a course
 public void enrollStudent(String studentNo, String courseId, String enrollDate) throws SQLException {
    String sql = "INSERT INTO ENROLLMENT (studentNo, courseId, enrollDate) VALUES (?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, studentNo);
    ps.setString(2, courseId);
    ps.setString(3, enrollDate);
    ps.executeUpdate();
    ps.close();
 }
 
 //deleting an enrollment
 public void deleteEnrollment(String studentNo, String courseId, String enrollDate) throws SQLException {
     String sql = "DELETE FROM ENROLLMENT WHERE studentNo = ? AND courseId = ?";
     PreparedStatement ps = conn.prepareStatement(sql);
     ps.setString(1, studentNo);
     ps.setString(2, courseId);
     ps.executeUpdate();
     ps.close();
 }
 
}
