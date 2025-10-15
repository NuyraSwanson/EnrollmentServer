
package za.ac.cput.serversideproject.WorkerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ADP Final assignment
 */

public class Admin implements Serializable {
   private String adminId;
    private String name;
    private String surname;
    private String password;
    private String email;

    public Admin(String adminId, String name, String surname, String password, String email) {
        this.adminId = adminId;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
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
