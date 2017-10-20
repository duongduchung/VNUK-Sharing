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
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

public class GeneratingDummyData {

    public GeneratingDummyData(){
        super();
    }

    // tao child courses
    public void generateCourseOnFirebaseDatabase(int numberOfCourses, DatabaseReference databaseReference){
        Course course;
        DatabaseReference coursesKey = databaseReference.child("root").child("courses");

        for(int i = 0; i < numberOfCourses; i++){
            course = generateCourse(i);
            coursesKey.push().setValue(course);
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
    public Course generateCourse(int id){
        Course course = new Course();

        course.setId(id);
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
            databaseChildClasses = databaseReference.child("root").child("classes");
            databaseChildClasses.push().setValue(classOfCourse);

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

    // tao child users và teacher
    public void generateUsersOnFirebaseDatabase(int numberOfCourses, DatabaseReference databaseReference){
        ArrayList<User> userArrayList = generateListUsers(10);

        DatabaseReference database, databaseTeacher;
        Teacher teacher;

        for (User user: userArrayList) {
            database = databaseReference.child("root").child("users").push();
            database.setValue(user);

            // teacher
            if(user.getAccess() == 1){
                teacher = generateATeacher(user.getId(), numberOfCourses);
                databaseTeacher = databaseReference.child("root").child("teachers").push();
                databaseTeacher.setValue(teacher);
            }
        }
    }
    public Teacher generateATeacher(int id, int numberOfCourseIds){
        Teacher teacher = new Teacher();

        teacher.setId(id);
        ArrayList<Integer> listOfCourseIds = new ArrayList<Integer>();

        for(int i = 0; i < numberOfCourseIds; i++){
            listOfCourseIds.add((new Random()).nextInt(numberOfCourseIds));
        }

        teacher.setListOfCourseIds(listOfCourseIds);

        return teacher;
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
