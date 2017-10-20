package vn.edu.vnuk.vnuk_sharing.Test;

import com.google.firebase.database.DatabaseReference;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Annoucement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.ClassOfCourse;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

public class GeneratingDummyData {

    public GeneratingDummyData(){
        super();
    }

    // tao child courses
    public void generateCourseOnFirebaseDatabase(DatabaseReference databaseReference){
        Course course = generateCourse();
        DatabaseReference coursesKey = databaseReference.child("root").child("courses").push();
        coursesKey.child("id").setValue(course.getId());
        coursesKey.child("idTeacher").setValue(course.getIdTeacher());
        coursesKey.child("name").setValue(course.getName());
        coursesKey.child("classOfCourse").setValue(course.getClassOfCourse());

        int count = 0;
        for(Deadline deadline : course.getListOfDeadlines()){
            coursesKey.child("deadlines").child(String.valueOf(count)).setValue(deadline);
            count++;
        }

        count = 0;
        for(Annoucement annoucement : course.getListOfAnnoucements()){
            coursesKey.child("annoucements").child(String.valueOf(count)).setValue(annoucement);
            count++;
        }
    }
    public ArrayList<Deadline> generateListDeadlines(int numberOfDeadlines){
        ArrayList<Deadline> listDeadlines = new ArrayList<Deadline>();

        Deadline deadline;

        for(int i = 0; i < numberOfDeadlines; i++){
            deadline = new Deadline();
            deadline.setId(i);
            deadline.setDateTime(DateFormat.getDateTimeInstance().format(new java.util.Date()));
            deadline.setDescription("Description " + i);
            listDeadlines.add(deadline);
        }

        return listDeadlines;
    }
    public ArrayList<Annoucement> generateListAnnoucements(int numberOfAnnoucements){
        ArrayList<Annoucement> listAnnoucements = new ArrayList<Annoucement>();

        Annoucement annoucement;

        for(int i = 0; i < numberOfAnnoucements; i++){
            annoucement = new Annoucement();
            annoucement.setId(i);
            annoucement.setDateTime(DateFormat.getDateTimeInstance().format(new java.util.Date()));
            annoucement.setDescription("Description " + i);
            listAnnoucements.add(annoucement);
        }

        return listAnnoucements;
    }
    public Syllabus generateSyllabus(){
        Syllabus syllabus = new Syllabus();

        syllabus.setName("File name");
        syllabus.setLink("Link download");
        syllabus.setSize(5);

        return syllabus;
    }
    public ClassOfCourse generateClassOfCourse(){
        ClassOfCourse classOfCourse = new ClassOfCourse();

        classOfCourse.setId(1);
        classOfCourse.setName("Class");

        return classOfCourse;
    }
    public Course generateCourse(){
        Course course = new Course();

        course.setId(1);
        course.setName("Course");
        course.setClassOfCourse(generateClassOfCourse());
        course.setListOfDeadlines(generateListDeadlines(5));
        course.setListOfAnnoucements(generateListAnnoucements(5));

        return course;
    }

    // tao child classes
    public void generateListClassOfCoursesOnFirebaseDatabase(DatabaseReference databaseReference){
        ArrayList<ClassOfCourse> classOfCourseArrayList = generateListClassOfCourses(10);

        DatabaseReference databaseChildClasses;

        for(ClassOfCourse classOfCourse : classOfCourseArrayList) {
            databaseChildClasses = databaseReference.child("root").child("classes").push();
            databaseChildClasses.setValue(classOfCourse);

        }
    }
    public ArrayList<ClassOfCourse> generateListClassOfCourses(int numberOfClasses){
        ArrayList<ClassOfCourse> classOfCourseArrayList = new ArrayList<ClassOfCourse>();

        ClassOfCourse classOfCourse;

        for(int i = 0; i < numberOfClasses; i++){
            classOfCourse = new ClassOfCourse();
            classOfCourse.setId(i);
            classOfCourse.setName("Class name " + i);
            classOfCourseArrayList.add(classOfCourse);
        }

        return classOfCourseArrayList;
    }

    // tao child users
    public void generateUsersOnFirebaseDatabase(DatabaseReference databaseReference){
        ArrayList<User> userArrayList = generateListUsers(10);

        DatabaseReference database;

        for (User user: userArrayList) {
            database = databaseReference.child("root").child("users").push();
            database.setValue(user);
        }
    }
    public ArrayList<User> generateListUsers(int numberOfUsers){
        ArrayList<User> userArrayList = new ArrayList<User>();

        Random random = new Random();

        User user;
        for(int i = 0; i < numberOfUsers; i++){
            user = new User();
            user.setId(i);
            user.setUsername("username" + i);
            user.setPassword("password" + i);
            user.setAccess(random.nextInt(2));
            userArrayList.add(user);
        }

        return userArrayList;
    }
}
