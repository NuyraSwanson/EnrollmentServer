
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.ac.cput.shared.WorkerClasses.Enroll;

/**
 * ADP Final assignment
 */

public class EnrollDAO {
 private Connection conn;
 
 public EnrollDAO(Connection conn) {
     this.conn = conn;
 }
 
 //enrolling a student in a course
 public void enrollStudent(Enroll e) throws SQLException {
    String sql = "INSERT INTO ENROLLMENT (studentNo, courseId) VALUES (?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, e.getStudentId());
    ps.setString(2, e.getCourseId());
    ps.executeUpdate();
    ps.close();
 }
 
 //deleting an enrollment
 public void deleteEnrollment(String studentNo, String courseId) throws SQLException {
     String sql = "DELETE FROM ENROLLMENT WHERE studentNo = ? AND courseId = ?";
     PreparedStatement ps = conn.prepareStatement(sql);
     ps.setString(1, studentNo);
     ps.setString(2, courseId);
     ps.executeUpdate();
     ps.close();
 }
 
}
