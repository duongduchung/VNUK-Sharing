package vn.edu.vnuk.vnuk_sharing.DataTemp;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Notification;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Student;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

/**
 * Created by Quangngoc430 on 10/21/2017.
 */

public class Data {
    public static boolean isReceiveDeadlineNotification;
    public static boolean isReceiveAnnouncementNotification;
    public static int currentNumberOfNotifications;
    public static Notification currentNotification;
    public static Deadline currentDeadline;
    public static Announcement currentAnnouncement;
    public static Syllabus currentSyllabus;
    public static User currentUser;
    public static Teacher currentTeacher;
    public static Student currentStudent;
    public static ArrayList<Course> courseArrayList;
    public static Course currentCourse;
    public static ArrayList<Deadline> deadlineArrayList;
    public static ArrayList<Announcement> announcementArrayList;

    public static void clearAllData(){
        currentNumberOfNotifications = -1;
        currentNotification = null;
        currentDeadline = null;
        currentAnnouncement = null;
        currentSyllabus = null;
        currentUser = null;
        currentTeacher = null;
        courseArrayList.clear();
        currentCourse = null;
        deadlineArrayList.clear();
        announcementArrayList.clear();
    }

    public static void initialAllData(){
        currentNumberOfNotifications = -1;
        currentNotification = new Notification();
        currentDeadline = new Deadline();
        currentAnnouncement = new Announcement();
        currentSyllabus = new Syllabus();
        currentUser = new User();
        currentTeacher = new Teacher();
        courseArrayList = new ArrayList<>();
        currentCourse = new Course();
        deadlineArrayList = new ArrayList<>();
        announcementArrayList = new ArrayList<>();
    }
}
