
package za.ac.cput.shared.WorkerClasses;

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
    
    //constructor
    public Course(String courseID, String courseName, int credits){
    this.courseID = courseID;
    this.courseName = courseName;
    this.credits = credits;

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
    
   
    
    @Override
    public String toString(){
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits='" + credits + 
                '}';
    }  
}
