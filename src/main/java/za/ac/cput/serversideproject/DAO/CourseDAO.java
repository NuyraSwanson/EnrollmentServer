
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.serversideproject.WorkerClasses.Course;

/**
 * ADP Final assignment
 */

public class CourseDAO {
  private Connection conn;

public CourseDAO(Connection conn) {
    this.conn = conn;
}

//finding course by its Id
public Course findCourse(String courseId) throws SQLException {
 String sql = "SELECT * FROM COURSE WHERE courseId = ?";
 PreparedStatement ps = conn.prepareStatement(sql);
 ps.setString(1, courseId);
 ResultSet rs = ps.executeQuery();
 
 Course course = null;
 if (rs.next()) {
     course = new Course (
             rs.getString("courseId"),
             rs.getString("courseName"),
             rs.getInt("credits")
     );
 }
 
 rs.close();
 ps.close();
 return course;
}

public void addCourse(Course c) throws SQLException {
    String sql = "INSERT INTO COURSE (courseId, courseName, credits, department, duration) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, c.getCourseID());
    ps.setString(2, c.getCourseName());
    ps.setInt(3, c.getCredits());
    
    ps.executeUpdate();
    ps.close();
}

//getting all courses
public List<Course> getAllCourses() throws SQLException {
 List<Course> courses = new ArrayList<>();
 String sql = "SELECT * FROM COURSE";
 Statement st = conn.createStatement();
 ResultSet rs = st.executeQuery(sql);
 
 while (rs.next()) {
     courses.add(new Course(
             rs.getString("courseId"),
             rs.getString("courseName"),
             rs.getInt("credits")
     ));
 }
 
 rs.close();
 st.close();
 return courses;
}

//deleting a course 
public void deleteCourse(String courseId) throws SQLException {
    String sql = "DELETE FROM COURSE WHERE courseId = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, courseId);
    ps.executeQuery();
    ps.close();
}
}
