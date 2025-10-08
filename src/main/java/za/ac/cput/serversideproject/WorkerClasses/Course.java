
package za.ac.cput.serversideproject.WorkerClasses;

import java.io.Serializable;

/**
 * ADP Final assignment
 */

public class Course implements Serializable {
  private static final long serialVersionUID = 1L;
    
  //course attributes
    private String courseID;
    private String courseName;
    private int credits;
    private String department;
    private int duration;
    
    //constructor
    public Course(String courseID, String courseName, int credits, String department, int duration){
    this.courseID = courseID;
    this.courseName = courseName;
    this.credits = credits;
    this.department = department;
    this.duration = duration;

    }
    
    //Getters
    public String getCourseID(){
        return courseID;
    }
    
    public String getCourseName(){
        return courseName;
    }
    
    public int getCredits(){
        return credits;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public int getDuration() {
        return duration;
    }
    
    //Setters
    public void setCourseID(String courseID){
        this.courseID = courseID;
    }
    
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    
    public void setCredits(int credits){
        this.credits = credits;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString(){
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", department='" + department + '\'' +
                ", duration='" + duration + '\'' +
                ", credits='" + credits + 
                '}';
    }  
}
