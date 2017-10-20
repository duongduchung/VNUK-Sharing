package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Course {
    private int id;
    private String courseCode;
    private String name;
    private ClassOfCourse classOfCourse;
    private ArrayList<Deadline> listOfDeadlines;
    private ArrayList<Annoucement> listOfAnnoucements;

    public Course(){
        super();
    }
    public Course(int id, String courseCode, String name, ClassOfCourse classOfCourse, ArrayList<Deadline> listOfDeadlines, ArrayList<Annoucement> listOfAnnoucements){
        super();
        this.id = id;
        this.courseCode = courseCode;
        this.name = name;
        this.classOfCourse = classOfCourse;
        this.listOfDeadlines = listOfDeadlines;
        this.listOfAnnoucements = listOfAnnoucements;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }
    public String getCourseCode(){
        return this.courseCode;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setClassOfCourse(ClassOfCourse classOfCourse){
        this.classOfCourse = classOfCourse;
    }
    public ClassOfCourse getClassOfCourse(){
        return this.classOfCourse;
    }
    public void setListOfDeadlines(ArrayList<Deadline> listOfDeadlines){
        this.listOfDeadlines = listOfDeadlines;
    }
    public ArrayList<Deadline> getListOfDeadlines() {
        return listOfDeadlines;
    }
    public void setListOfAnnoucements(ArrayList<Annoucement> listOfAnnoucements) {
        this.listOfAnnoucements = listOfAnnoucements;
    }
    public ArrayList<Annoucement> getListOfAnnoucements() {
        return listOfAnnoucements;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
