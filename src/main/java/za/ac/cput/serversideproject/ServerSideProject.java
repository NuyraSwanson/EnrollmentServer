

package za.ac.cput.serversideproject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import za.ac.cput.serversideproject.DAO.AdminDAO;
import za.ac.cput.serversideproject.DAO.CourseDAO;
import za.ac.cput.serversideproject.DAO.EnrollDAO;
import za.ac.cput.serversideproject.DAO.StudentDAO;
import za.ac.cput.serversideproject.DBConnection.DBConnection;
import za.ac.cput.serversideproject.WorkerClasses.Admin;
import za.ac.cput.serversideproject.WorkerClasses.Course;
import za.ac.cput.serversideproject.WorkerClasses.Enroll;
import za.ac.cput.serversideproject.WorkerClasses.Student;


/**
 * ADP Final assignment
 */

public class ServerSideProject {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private Connection conn;
  
  private StudentDAO studentDAO;
  private AdminDAO adminDAO;
  private EnrollDAO enrollDAO;
  private CourseDAO courseDAO;
  
  public ServerSideProject(){
      try {
          // Start server
            serverSocket = new ServerSocket(5678);
            System.out.println("Server started. Waiting for connection...");

            conn = DBConnection.getConnection();
            System.out.println("Database connection established!");

            studentDAO = new StudentDAO(conn);
            adminDAO = new AdminDAO(conn);
            enrollDAO = new EnrollDAO(conn);
            courseDAO = new CourseDAO(conn);

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Server socket error: " + e.getMessage());
            e.printStackTrace();
        }
    }
  
  public void start() {
      while (true) {
          try {
              clientSocket = serverSocket.accept();
              System.out.println("Client connected!");
              
              handleClient(clientSocket);
              
} catch (IOException e) {
    System.out.println("Error accepting client: " + e.getMessage());
          }
      }
  }
  
  private void handleClient(Socket clientSocket) {
      try {
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          
          while (true) {
              String command = (String) in.readObject();
              
              switch (command) {
                  case "LOGIN" : {
                      String role = (String) in.readObject();
                      String username = (String) in.readObject();
                      String password = (String) in.readObject();
                      
                      if (role.equalsIgnoreCase("STUDENT")){
                          Student s = studentDAO.findStudent(username);
                          if (s != null && s.getPassword().equals(password)) {
                              out.writeObject("STUDENT_SUCCESS!");
                              System.out.println("Student login; " + username);
                          } else {
                              out.writeObject("FAIL");
                          }
                          
                      } else if (role.equalsIgnoreCase("ADMIN")) {
                          boolean valid = adminDAO.validateAdmin(username, password);
                          if (valid) {
                              out.writeObject("ADMIN_SUCCESS!");
                              System.out.println("Admin login: " + username);
                          } else {
                              out.writeObject("FAIL");
                          }
                      }
                      out.flush();
                      break;
                  }
                  
                  case "ADD_STUDENT" : {
                      Student s = (Student) in.readObject();
                      studentDAO.addStudent(s);
                      out.writeObject("STUDENT_ADDED");
                      System.out.println("New student added: " + s.getName());
                      out.flush();
                      break;
                  }
                  
                  case "ADD_COURSE" : {
                      Course c = (Course) in.readObject();
                      courseDAO.addCourse(c);
                      out.writeObject("COURSE_ADDED");
                      System.out.println("New course added: " + c.getCourseName());
                      out.flush();
                      break;
                      
                  }
                  
                  case "VIEW_COURSES" : {
                      List<Course> courses = courseDAO.getAllCourses();
                      out.writeObject(courses);
                      System.out.println("Sent course list to client (" + courses.size() + " courses)");
                      out.flush();
                      break;
                      
                  }
                  
                  case "VIEW_STUDENTS" : {
                      List<Student> students = studentDAO.getAllStudents();
                      out.writeObject(students);
                      System.out.println("Sent student list to client (" + students.size() + " students)");
                      out.flush();
                      break;
                  }
                  
                  case "ENROLL" : {
                      Enroll e = (Enroll) in.readObject();
                      enrollDAO.enrollStudent(e);
                      out.writeObject("ENROLL_SUCCESS");
                      System.out.println("Student enrolled: " + e.getStudentId() + " in course " + e.getCourseId());
                      out.flush();
                      break;
                      
                  }
                  
                  case "GET_ADMIN" : {
                      String username = (String) in.readObject();
                      Admin admin = adminDAO.findAdmin(username);
                      out.writeObject(admin);
                      out.flush();
                      System.out.println("Send admin details for: " + username);
                      break;
                  }
                  
                  case "VIEW_ADMINS": {
                     List<Admin> admins = adminDAO.getAllAdmins();
                     out.writeObject(admins);
                     out.flush();
                     System.out.println("Sent admin list to client (" + admins.size() + " admins)");
                     
                     break;
                  } 

               default:
                 out.writeObject("UNKNOWN COMMAND");
                 out.flush();
                 System.out.println("Unknown command");
                 break;

              }
              }
         }catch (IOException | ClassNotFoundException | SQLException e) {

          }
      }
  
    public static void main(String[] args) {
        ServerSideProject server = new ServerSideProject();
        server.start();
    }
}
