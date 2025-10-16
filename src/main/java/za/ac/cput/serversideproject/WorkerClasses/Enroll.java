
package za.ac.cput.serversideproject.WorkerClasses;

import java.io.Serializable;

/**
 * ADP Final assignment
 */
public class Enroll implements Serializable {
 private static final long serialVersionUID = 1L;
 private String studentId;
 private String courseId;
 private String enrollDate;
 
 public Enroll(String studentId, String courseId, String enrollDate) {
     this.studentId = studentId;
     this.courseId = courseId;
     this.enrollDate = enrollDate;
 }
 
 public Enroll() {
     
 }
 
 //Getters
 public String getStudentId() {
     return studentId;
 }
 
 public String getCourseId() {
     return courseId;
 }
 
 public String getEnrollDate() {
     return enrollDate;
 }
 
 //Setters
 public void setStudentId(String studentid) {
     this.studentId = studentId;
 }
 
 public void setCourseId(String courseId) {
     this.courseId = courseId;
 }
 
 public void setEnrollDate(String enrollDate) {
     this.enrollDate = enrollDate;
 }
 
 @Override
 public String toString() {
     return "Enroll{" +
             "studentId='" + studentId + '\'' +
             ", courseId='" + courseId + '\'' + 
             ", enrollDate='" + enrollDate + 
             '}';
 }
    
}
