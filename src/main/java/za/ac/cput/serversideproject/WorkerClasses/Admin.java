
package za.ac.cput.serversideproject.WorkerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ADP Final assignment
 */

public class Admin implements Serializable {
   private static final long serialVersionUID = 1L;
   private String adminId;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String contactNo;
    private String role;

    public Admin(String adminId, String password, String name, String surname, String email, String contactNo, String role) {
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contactNo = contactNo;
        this.role = role;
    }
    
    //getters
    public String getAdminId() { 
        return adminId; 
    }
    public String getName() { 
        return name; 
    }
    public String getSurname() { 
        return surname; 
    }
    public String getPassword() { 
        return password; 
    }
    public String getEmail() { 
        return email; 
    }
    
    public String getRole() {
        return role;
    }
    
    public String getContactNo() {
        return contactNo;
    }
    
    //setters
    public void setAdminId(String adminId) { 
        this.adminId = adminId; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public void setSurname(String surname) { 
        this.surname = surname; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    //custom serialization
   private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    out.writeObject("*****");
   }
   
   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    password = "default";
   }
   
    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    } 
}
