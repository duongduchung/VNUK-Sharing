package vn.edu.vnuk.vnuk_sharing.Test;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Class;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Student;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;
import vn.edu.vnuk.vnuk_sharing.SHA256;

public class GeneratingDummyData {

    private ArrayList<Class> classArrayList = new ArrayList<Class>();
    private ArrayList<Course> courseArrayList = new ArrayList<Course>();
    private ArrayList<Teacher> teachersArrayList = new ArrayList<Teacher>();
    private ArrayList<Student> studentArrayList = new ArrayList<Student>();
    private ArrayList<Boolean> coursesCheckArrayList = new ArrayList<Boolean>();
    int numberOfCourses, randomNumber, numberOfClasses;

    public GeneratingDummyData(){
    }

    public void createData(int numberOfCourses, int numberOfUsers, int numberOfClasses){
        this.numberOfCourses = numberOfCourses;
        this.numberOfClasses = numberOfClasses;
        classArrayList = generateClasses(numberOfClasses);
        courseArrayList = generateCoursesArrayList(numberOfCourses, numberOfClasses);
        generateUsersArrayList(numberOfUsers);
    }


    public ArrayList<Class> generateClasses(int numberOfClasses){
        ArrayList<Class> classArrayList = new ArrayList<>();

        for(int i = 0; i < numberOfClasses; i++){
            Class newClass = new Class();
            newClass.setId(i);
            newClass.setName("ClassName : " + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("classes").child("class" + "-" + newClass.getId()).setValue(newClass);

            classArrayList.add(newClass);
        }

        return classArrayList;
    }

    public ArrayList<Course> generateCoursesArrayList(int numberOfCourses, int numberOfClasses){
        ArrayList<Course> coursesArrayList = new ArrayList<Course>();

        for(int i = 0; i < numberOfCourses; i++){
            coursesArrayList.add(generateSingleCourse(i, numberOfClasses));
        }

        return coursesArrayList;
    }
    public Course generateSingleCourse(int id, int numberOfClasses){
        Course course = new Course();
        course.setId(id);
        course.setCodeCourse("codeCore" + id);
        course.setIdClass((new Random()).nextInt(numberOfClasses));
        course.setIdTeacher(-1);
        course.setName("course name " + id);
        course.setAnnoucementsCount(gerenateAnnoncements(2 + (new Random()).nextInt(10), id));
        course.setDeadlinesCount(generateDeadlines(2 + (new Random()).nextInt(10), id));
        course.setStatus(1);
        generateSyllabus(id);

        FirebaseDatabase.getInstance().getReference().child("root").child("courses").child("course" + "-" + course.getId()).setValue(course);

        return course;
    }
    public Syllabus generateSyllabus(int idCourse){
        Syllabus syllabus = new Syllabus();
        syllabus.setIdCourse(idCourse);
        syllabus.setName("Syllabus " + idCourse);
        syllabus.setSize((new Random()).nextInt(40000));
        syllabus.setLink("Link syllabus " + idCourse);
        syllabus.setExists(false);

        FirebaseDatabase.getInstance().getReference().child("root").child("syllabuses").child("syllabus" + "-" + idCourse).setValue(syllabus);
        return syllabus;
    }
    public int gerenateAnnoncements(int announcementsCount, int idCourse){
        for(int i = 0; i < announcementsCount; i++){
            Announcement announcement = new Announcement();
            announcement.setId(i);
            announcement.setIdCourse(idCourse);
            announcement.setDate(new Date());
            announcement.setTitle("announcement " + i);
            announcement.setDescription("description of announcement " + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("announcements").child("course" + "-" + idCourse).child("announcement" + "-" + announcement.getId()).setValue(announcement);
        }

        return announcementsCount;
    }
    public int generateDeadlines(int deadlinesCount, int idCourse){
        for(int i = 0; i < deadlinesCount; i++){
            Deadline deadline = new Deadline();
            deadline.setId(i);
            deadline.setIdCourse(idCourse);
            deadline.setDate(new Date());
            deadline.setTitle("deadline " + i);
            deadline.setDescription("description of deadline " + i);

            FirebaseDatabase.getInstance().getReference().child("root").child("deadlines").child("course" + "-" + idCourse).child("deadline" + "-" + deadline.getId()).setValue(deadline);
        }

        return deadlinesCount;
    }

    public void generateUsersArrayList(int numberOfUsers){
        for(int i = 0; i < numberOfUsers; i++){
            generateSingleUser(i);
        }
    }
    public User generateSingleUser(int id){
        User user = new User();
        user.setId(id);
        user.setAccess((new Random()).nextInt(2));
        if(user.getAccess() == 0){
            generateSingleStudent(id);
        }else {
            generateSingleTeacher(id);
        }
        user.setUsername("username" + id);
        user.setPassword(SHA256.getSHA256Hash("password" + id));

        FirebaseDatabase.getInstance().getReference().child("root").child("users").child("user" + "-" + user.getUsername() + "-" + user.getPassword()).setValue(user);
        return user;
    }
    private Student generateSingleStudent(int idUser){
        Student newStudent = new Student();

        newStudent.setIdUser(idUser);
        newStudent.setName("student " + idUser);
        newStudent.setIdClass((new Random()).nextInt(numberOfClasses));
        newStudent.setIdCoursesArrayList(generateIdCoursesArrayListForStudent(5 + (new Random()).nextInt(10), idUser));

        FirebaseDatabase.getInstance().getReference().child("root").child("students").child("student" + "-" + idUser).setValue(newStudent);

        return newStudent;
    }
    public ArrayList<Integer> generateIdCoursesArrayListForStudent(int numberOfIdCourses, int idUser){
        ArrayList<Integer> idCoursesArrayList = new ArrayList<>();
        int temp;
        boolean check;

        for(int i = 0; i < numberOfIdCourses; i++){

            check = true;

            do{
                check = true;

                temp = (new Random()).nextInt(courseArrayList.size());

                for(Integer integer : idCoursesArrayList){
                    if(temp == integer){
                        check = false;
                        break;
                    }
                }

                if(check == true){
                    idCoursesArrayList.add(temp);
                }

            }while(check == false);
        }

        return idCoursesArrayList;
    }
    public Teacher generateSingleTeacher(int idUser){
        Teacher teacher = new Teacher();
        teacher.setIdUser(idUser);
        teacher.setName("teacher " + idUser);

        teacher.setIdCoursesArrayList(generateIdCoursesArrayListForTeacher(5 + (new Random()).nextInt(10), idUser));

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("teachers")
                .child("teacher" + "-" + idUser)
                .setValue(teacher);

        return teacher;
    }
    public ArrayList<Integer> generateIdCoursesArrayListForTeacher(int numberOfIdCourses, int idUser){
        ArrayList<Integer> idCoursesArrayList = new ArrayList<Integer>();
        int temp;
        boolean check;

        for(int i = 0; i < numberOfIdCourses; i++){

            check = true;

            do{
                check = true;

                temp = (new Random()).nextInt(courseArrayList.size());

                for(Integer integer : idCoursesArrayList){
                    if(temp == integer){
                        check = false;
                        break;
                    }
                }

                if(check == true){
                    idCoursesArrayList.add(temp);
                }

            }while(check == false);

            idCoursesArrayList.add(temp);
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child("root")
                    .child("courses")
                    .child("course" + "-" + idCoursesArrayList.get(i))
                    .child("idClass")
                    .setValue((new Random()).nextInt(classArrayList.size()));
        }

        return idCoursesArrayList;
    }

}










