package vn.edu.vnuk.vnuk_sharing.Test;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Class;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

public class GeneratingDummyData {

    public GeneratingDummyData(){
    }

    private ArrayList<Integer> listIdCourses = new ArrayList<Integer>();
    private int idTeacher = 0;
    private int idUser = 0;
    private int idCourse = 0;
    private final int status = 4;
    private final int numberOfAnnoucements = 10;
    private final int numberOfDeadlines = 10;
    private int numberOfClass;

    public void createData(int numberOfCourses, int numberOfUsers, int numberOfClasses){
        this.numberOfClass = numberOfClasses;
    }

    public void generateClasses(int numberOfClasses){
        for(int i = 0; i < numberOfClasses; i++){
            Class newClass = new Class();
            newClass.setId(i);
            newClass.setName("ClassName : " + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("classes").child("class" + "-" + newClass.getId()).setValue(newClass);
        }
    }



}
