package vn.edu.vnuk.vnuk_sharing.Test;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.ClassOfCourse;
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

        generateListIdCourses(numberOfCourses);
        generateListClasses(numberOfClasses);
        generateListUsers(numberOfUsers);
        generateListCourses(numberOfCourses);
    }

    public void generateListIdCourses(int numberOfCourses){
        for(int i = 0; i < numberOfCourses; i++){
            listIdCourses.add(i);
        }
    }

    // generate list classes
    public void generateListClasses(int numberOfClasses){
        for(int i = 0; i < numberOfClasses; i++){
            ClassOfCourse classOfCourse = new ClassOfCourse();

            classOfCourse.setId(i);
            classOfCourse.setName("Class" + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("classes").push().setValue(classOfCourse);
        }
    }

    // generate list users
    public void generateListUsers(int numberOfUsers){
        for(int i = 0; i < numberOfUsers; i++){
            generateSingleUser(idUser);
            idUser++;
        }
    }
    public User generateSingleUser(int idUser){
        User user = new User();

        user.setId(idUser);
        user.setUsername("username" + idUser);
        user.setPassword("password" + idUser);
        user.setAccess((new Random()).nextInt(2));

        if(user.getAccess() == 1){
            generateSingleTeacher(idUser);
        }

        FirebaseDatabase.getInstance().getReference().child("root").child("users").push().setValue(user);

        return user;
    }
    public Teacher generateSingleTeacher(int idUser){
        Teacher teacher = new Teacher();

        teacher.setId(idTeacher);
        idTeacher++;
        teacher.setIdUser(idUser);

        FirebaseDatabase.getInstance().getReference().child("root").child("teachers").push().setValue(teacher);
        return teacher;
    }

    // generate list courses
    public void generateListCourses(int numberOfCourses){
        for(int i = 0; i < numberOfCourses; i++){
            generateSingleCourse(i);
        }
    }
    public Course generateSingleCourse(int idCourse){
        Course course = new Course();

        course.setId(idCourse);
        course.setCodeCourse("codeCoure" + idCourse);
        course.setIdTeacher((new Random()).nextInt(idTeacher));
        course.setIdClassOfCourse((new Random()).nextInt(numberOfClass));
        course.setName("course" + idCourse);
        course.setStatus((new Random()).nextInt(status));
        course.setAnnoucementIdArrayList(generateListOfAnnoucement((new Random()).nextInt(numberOfAnnoucements), idCourse));
        course.setDeadlineIdArrayList(generateListOfDeadline((new Random()).nextInt(numberOfAnnoucements), idCourse));
        generateSyllabus(idCourse);

        FirebaseDatabase.getInstance().getReference().child("root").child("courses").push().setValue(course);

        return course;
    }
    public ArrayList<Integer> generateListOfAnnoucement(int numberOfAnnoucements, int idCourse){
        ArrayList<Integer> annoucementArrayList = new ArrayList<>();

        for(int i = 0; i < numberOfAnnoucements; i++){
            Announcement announcement = new Announcement();
            announcement.setId(i);
            announcement.setIdCourse(idCourse);
            announcement.setDate(new Date());
            announcement.setTitle("announcement" + i);
            announcement.setDescription("description" + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("announcements").push().setValue(announcement);
        }

        return annoucementArrayList;
    }
    public ArrayList<Integer> generateListOfDeadline(int numberOfDeadlines, int idCourse){
        ArrayList<Integer> deadlineIdArrayList = new ArrayList<>();
        for(int i = 0; i < numberOfDeadlines; i++){
            Deadline deadline = new Deadline();
            deadline.setId(i);
            deadline.setIdCourse(idCourse);
            deadline.setDate((new Date()));
            deadline.setTitle("deadline" + i);
            deadline.setDescription("description" + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("deadlines").push().setValue(deadline);
        }

        return deadlineIdArrayList;
    }
    public Syllabus generateSyllabus(int idCourse){
        Syllabus syllabus = new Syllabus();

        syllabus.setIdCourse(idCourse);
        syllabus.setName("Syllabus" + idCourse);
        syllabus.setLink("Link" + idCourse);
        syllabus.setSize(5000);

        FirebaseDatabase.getInstance().getReference().child("root").child("syllabuses").push().setValue(syllabus);

        return syllabus;
    }
}
