
package za.ac.cput.shared.WorkerClasses;

import java.io.Serializable;

/**
 * ADP Final assignment
 */
public class Enroll implements Serializable {
 private static final long serialVersionUID = 1L;
 private String studentId;
 private String courseId;
 
 
 public Enroll(String studentId, String courseId) {
     this.studentId = studentId;
     this.courseId = courseId;
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
 
 
 //Setters
 public void setStudentId(String studentid) {
     this.studentId = studentId;
 }
 
 public void setCourseId(String courseId) {
     this.courseId = courseId;
 }
 
 
 @Override
 public String toString() {
     return "Enroll{" +
             "studentId='" + studentId + '\'' +
             ", courseId='" + courseId + '\'' + 
             '}';
 }
    
}
