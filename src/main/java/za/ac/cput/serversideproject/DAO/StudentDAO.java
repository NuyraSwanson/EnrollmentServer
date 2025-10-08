
package za.ac.cput.serversideproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.serversideproject.WorkerClasses.Student;

/**
 * ADP Final assignment
 */

public class StudentDAO {
    private Connection conn;
    
    public StudentDAO(Connection conn) {
        this.conn = conn;
    }
    
    //adding a new student
    public void addStudent(Student s) throws SQLException {
      String sql = "INSERT INTO STUDENT (studentNo, name, surname, password, gender, DOB, phoneNo, emailAdd) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, s.getStudentNo());
      ps.setString(2, s.getName());
      ps.setString(3, s.getSurname());
      ps.setString(4, s.getPassword());
      ps.setString(5, String.valueOf(s.getGender()));
      ps.setString(6, s.getDOB());
      ps.setString(7, s.getPhoneNo());
      ps.setString(8, s.getEmailAdd());
      ps.executeUpdate();
      ps.close();
    }
    
    //finding student details for login
    public Student findStudent(String studentNo) throws SQLException {
        String sql = "SELECT * FROM STUDENT WHERE studentNo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, studentNo);
        ResultSet rs = ps.executeQuery();
        
        Student student = null;
        if (rs.next()) {
            student = new Student(
                    rs.getString("studentNo"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("password"),
                    rs.getString("gender").charAt(0),
                    rs.getString("dob"),
                    rs.getString("phoneNo"),
                    rs.getString("emailAdd")
            );
        }
        rs.close();
        ps.close();
        return student;
        
    }
    
    //displaying all students for admin account
    public List<Student> getAllStudents() throws SQLException {
      List<Student> students = new ArrayList<>();
      String sql = "SELECT * FROM STUDENT";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sql);
      
      while (rs.next()) {
          students.add(new Student(
                    rs.getString("studentNo"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("password"),
                    rs.getString("gender").charAt(0),
                    rs.getString("dob"),
                    rs.getString("phoneNo"),
                    rs.getString("emailAdd")
          ));
      }
      
      rs.close();
      st.close();
      return students;
    }
    
    
}
