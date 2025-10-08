
package za.ac.cput.serversideproject.WorkerClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ADP Final assignment
 */

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //variables/attributes for students
   private String studentNo;
   private String name;
   private String surname;
   private char gender;
   private String phoneNo;
   private String emailAdd;
   private String dob;
   private transient String password;
   
   //constructor
   public Student(String studentNo, String name, String surname, String password, char gender, String dob, String phoneNo, String emailAdd){
       this.studentNo = studentNo;
       this.name = name;
       this.password = password;
       this.surname = surname;
       this.dob = dob;
       this.emailAdd = emailAdd;
       this.phoneNo = phoneNo;
       this.gender = gender;
       
   }
   
   //Getters
   public String getStudentNo(){
       return studentNo;
   }
   
   public String getName(){
       return name;
   }
   
   public String getPassword(){
       return password;
   }
   
   public String getSurname() {
       return surname;
   }
   
   public String getDOB() {
       return dob;
   }
   
   public String getPhoneNo() {
       return phoneNo;
   }
   
   public String getEmailAdd() {
       return emailAdd;
   }
   
   public char getGender() {
     return gender;  
   }
   
   //Setters
   public void setStudentNo(String studentNo){
       this.studentNo = studentNo;
   }
   
   public void setName(String name){
       this.name = name;
   }
   
   public void setPassword(String password){
       this.password = password;
   }
   
   public void setSurname(String surname) {
       this.surname = surname;
   }
   
   public void setGender(char gender) {
       this.gender = gender;
   }
   
   public void setPhoneNo(String phoneNo) {
       this.phoneNo = phoneNo;
   }
   
   public void setEmailAdd(String emailAdd) {
       this.emailAdd = emailAdd;
   }
   
   public void setDob(String dob) {
       this.dob = dob;
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
    return "Student{" +
            "studentNo='" + studentNo + '\'' +
            "name='" + name + '\'' +
            "surname='" + surname + '\'' +
            "gender='" + gender + '\'' +
            "emailAdd='" + emailAdd + '\'' +
            "phoneNo='" + phoneNo + '\'' + 
            ", dob='" + dob + '\'' +
            '}';
}
}
