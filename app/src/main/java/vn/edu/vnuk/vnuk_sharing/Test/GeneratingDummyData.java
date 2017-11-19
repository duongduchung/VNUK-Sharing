package vn.edu.vnuk.vnuk_sharing.Test;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Class;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Notification;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Student;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;
import vn.edu.vnuk.vnuk_sharing.Methods.SHA256;

public class GeneratingDummyData {

    public static final int MAX_ID_NOTIFICATION = 2000000000;
    private final int numberOfAnnouncements = 10;
    private final int numberOfDeadlines = 10;
    private ArrayList<Class> classArrayList = new ArrayList<Class>();
    private ArrayList<Course> courseArrayList = new ArrayList<Course>();
    private ArrayList<Teacher> teachersArrayList = new ArrayList<Teacher>();
    private ArrayList<Student> studentArrayList = new ArrayList<Student>();
    private ArrayList<Boolean> coursesCheckArrayList = new ArrayList<Boolean>();
    int numberOfCourses, randomNumber, numberOfClasses;

    public GeneratingDummyData(){
    }

    public void createData(int numberOfCourses, int numberOfUsers, int numberOfClasses){
        FirebaseDatabase
                .getInstance()
                .getReference()
                .removeValue();
        this.numberOfCourses = numberOfCourses;
        this.numberOfClasses = numberOfClasses;
        generateNotifications();
        classArrayList = generateClasses(numberOfClasses);
        courseArrayList = generateCoursesArrayList(numberOfCourses, numberOfClasses);
        generateUsersArrayList(numberOfUsers);

    }

    private void generateNotifications(){
        int numberOfNotifications = numberOfCourses * (numberOfAnnouncements + numberOfDeadlines + 1);
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("numberOfNotification")
                .setValue(numberOfNotifications);

        for(int i = 0; i < numberOfCourses; i++){
            Notification newNotificationOfAnnouncement;
            for(int j = 0; j < numberOfAnnouncements; j++){
                newNotificationOfAnnouncement = new Notification();
                newNotificationOfAnnouncement.setIdNotification(i * (numberOfAnnouncements + numberOfDeadlines + 1) + j);
                newNotificationOfAnnouncement.setIdCourse(i);
                newNotificationOfAnnouncement.setNameCourse("Course " + i);
                newNotificationOfAnnouncement.setTitleOfNotification("Add an announcement " + j + " course " + i);
                newNotificationOfAnnouncement.setContentOfNotification("Content of add an announcement " + j + " course " + i);
                newNotificationOfAnnouncement.setTypeOfNotification(2);
                newNotificationOfAnnouncement.setIdAnnouncement(j);
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("notifications")
                        .child("notification-" + (MAX_ID_NOTIFICATION - newNotificationOfAnnouncement.getIdNotification()))
                        .setValue(newNotificationOfAnnouncement);
            }

            for(int j = 0; j < numberOfDeadlines; j++){
                Notification newNotificationOfDeadline = new Notification();
                newNotificationOfDeadline.setIdNotification(i * (numberOfAnnouncements + numberOfDeadlines + 1) + numberOfAnnouncements +j);
                newNotificationOfDeadline.setIdCourse(i);
                newNotificationOfDeadline.setNameCourse("Course " + i);
                newNotificationOfDeadline.setTitleOfNotification("Add a deadline " + j + " course " + i);
                newNotificationOfDeadline.setContentOfNotification("Content of add a deadline " + j + " course " + i);
                newNotificationOfDeadline.setTypeOfNotification(1);
                newNotificationOfDeadline.setIdDeadline(j);
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("notifications")
                        .child("notification-" + (MAX_ID_NOTIFICATION - newNotificationOfDeadline.getIdNotification()))
                        .setValue(newNotificationOfDeadline);
            }

            Notification newNotificationOfSyllabus = new Notification();
            newNotificationOfSyllabus.setIdNotification(i * (numberOfAnnouncements + numberOfDeadlines + 1) + numberOfAnnouncements + numberOfDeadlines);
            newNotificationOfSyllabus.setIdCourse(i);
            newNotificationOfSyllabus.setNameCourse("Course " + i);
            newNotificationOfSyllabus.setTitleOfNotification("Update syllabus course " + i);
            newNotificationOfSyllabus.setContentOfNotification("Content of Update syllabus course " + i);
            newNotificationOfSyllabus.setTypeOfNotification(0);
            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("root")
                    .child("notifications")
                    .child("notification-" + (MAX_ID_NOTIFICATION - newNotificationOfSyllabus.getIdNotification()))
                    .setValue(newNotificationOfSyllabus);
        }
    }
    private ArrayList<Class> generateClasses(int numberOfClasses){
        ArrayList<Class> classArrayList = new ArrayList<>();

        for(int i = 0; i < numberOfClasses; i++){
            Class newClass = new Class();
            newClass.setId(i);
            newClass.setName("ClassName : " + i);

            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("root")
                    .child("classes")
                    .child("class" + "-" + newClass.getId())
                    .setValue(newClass);

            classArrayList.add(newClass);
        }

        return classArrayList;
    }

    private ArrayList<Course> generateCoursesArrayList(int numberOfCourses, int numberOfClasses){
        ArrayList<Course> coursesArrayList = new ArrayList<Course>();

        for(int i = 0; i < numberOfCourses; i++){
            coursesArrayList.add(generateSingleCourse(i, numberOfClasses));
        }

        return coursesArrayList;
    }
    private Course generateSingleCourse(int id, int numberOfClasses){
        Course course = new Course();
        course.setId(id);
        course.setCodeCourse("codeCore" + id);
        course.setIdClass((new Random()).nextInt(numberOfClasses));
        course.setIdTeacher(-1);
        course.setName("course name " + id);
        course.setAnnouncementsCount(gerenateAnnoncements(numberOfAnnouncements, id));
        course.setDeadlinesCount(generateDeadlines(numberOfDeadlines, id));
        course.setStatus(1);
        generateSyllabus(id);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("courses")
                .child("course" + "-" + course.getId())
                .setValue(course);

        return course;
    }
    private Syllabus generateSyllabus(int idCourse){
        Syllabus syllabus = new Syllabus();
        syllabus.setIdCourse(idCourse);
        syllabus.setName("Syllabus " + idCourse);
        syllabus.setSize((new Random()).nextInt(40000));
        syllabus.setLink("Link syllabus " + idCourse);
        syllabus.setExists(false);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("syllabuses")
                .child("syllabus" + "-" + idCourse)
                .setValue(syllabus);

        return syllabus;
    }
    private int gerenateAnnoncements(int announcementsCount, int idCourse){
        for(int i = 0; i < announcementsCount; i++){
            Announcement announcement = new Announcement();
            announcement.setId(i);
            announcement.setIdCourse(idCourse);
            announcement.setDate(new Date());
            announcement.setTitle("announcement " + i);
            announcement.setDescription("description of announcement " + i);

            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("root")
                    .child("announcements")
                    .child("course" + "-" + idCourse)
                    .child("announcement" + "-" + announcement.getId())
                    .setValue(announcement);
        }



        return announcementsCount;
    }
    private int generateDeadlines(int deadlinesCount, int idCourse){
        for(int i = 0; i < deadlinesCount; i++){
            Deadline deadline = new Deadline();
            deadline.setId(i);
            deadline.setIdCourse(idCourse);
            deadline.setDate(new Date());
            deadline.setTitle("deadline " + i);
            deadline.setDescription("description of deadline " + i);

            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("root")
                    .child("deadlines")
                    .child("course" + "-" + idCourse)
                    .child("deadline" + "-" + deadline.getId())
                    .setValue(deadline);
        }

        return deadlinesCount;
    }

    private void generateUsersArrayList(int numberOfUsers){
        for(int i = 0; i < numberOfUsers; i++){
            generateSingleUser(i);
        }
    }
    private User generateSingleUser(int id){
        User user = new User();
        user.setId(id);
        user.setAccess((new Random()).nextInt(2));
        if(user.getAccess() == 1){
            generateSingleStudent(id);
        }else {
            generateSingleTeacher(id);
        }
        user.setUsername("username" + id);
        user.setPassword(SHA256.getSHA256Hash("password" + id));

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("users")
                .child("user" + "-" + user.getUsername() + "-" + user.getPassword())
                .setValue(user);

        return user;
    }
    private Student generateSingleStudent(int idUser){
        Student newStudent = new Student();

        newStudent.setIdUser(idUser);
        newStudent.setName("student " + idUser);
        newStudent.setIdClass((new Random()).nextInt(numberOfClasses));
        newStudent.setIdCoursesArrayList(generateIdCoursesArrayListForStudent(5 + (new Random()).nextInt(10), idUser));

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("students")
                .child("student" + "-" + idUser)
                .setValue(newStudent);

        return newStudent;
    }
    private ArrayList<Integer> generateIdCoursesArrayListForStudent(int numberOfIdCourses, int idUser){
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
    private Teacher generateSingleTeacher(int idUser){
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
    private ArrayList<Integer> generateIdCoursesArrayListForTeacher(int numberOfIdCourses, int idUser){
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










