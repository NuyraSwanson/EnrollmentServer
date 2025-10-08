

package za.ac.cput.serversideproject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import za.ac.cput.serversideproject.DAO.AdminDAO;
import za.ac.cput.serversideproject.DAO.CourseDAO;
import za.ac.cput.serversideproject.DAO.EnrollDAO;
import za.ac.cput.serversideproject.DAO.StudentDAO;
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
          serverSocket = new ServerSocket(5678);
          System.out.println("Server started. Waiting for connection...");
          
          conn = DriverManager.getConnection(
                  "jdbc:derby://localhost:1527/StudentEnrollmentDB", "administrator", "admin"
          );
          System.out.println("Database connection established!");
          
          studentDAO = new StudentDAO(conn);
          adminDAO = new AdminDAO(conn);
          enrollDAO = new EnrollDAO(conn);
          courseDAO = new CourseDAO(conn);
          
} catch (IOException | SQLException e) {
    System.out.println("Error: " + e.getMessage());
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

               default:
                 out.writeObject("UNKNOWN COMMAND");
                 out.flush();
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
