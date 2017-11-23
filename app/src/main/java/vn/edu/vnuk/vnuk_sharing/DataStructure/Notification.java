package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.HashMap;

/*
 * 0 - Syllabus
 * 1 - Deadline
 * 2 - Announcement
 * 3 - notification of university
 */

// ~/root/notification/notification-[idNotification]

public class Notification {
    private int idNotification;
    private int idCourse;
    private String nameCourse;
    private String titleOfNotification;
    private String contentOfNotification;
    private int idDeadline;
    private int idAnnouncement;
    private int idNews;
    private int typeOfNotification;

    public Notification() {
    }

    public Notification(int idNotification, int idCourse, String nameCourse, String titleOfNotification, String contentOfNotification, int idDeadline, int idAnnouncement, int idNews, int typeOfNotification) {
        this.idNotification = idNotification;
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.titleOfNotification = titleOfNotification;
        this.contentOfNotification = contentOfNotification;
        this.idDeadline = idDeadline;
        this.idAnnouncement = idAnnouncement;
        this.idNews = idNews;
        this.typeOfNotification = typeOfNotification;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitleOfNotification() {
        return titleOfNotification;
    }

    public void setTitleOfNotification(String titleOfNotification) {
        this.titleOfNotification = titleOfNotification;
    }

    public String getContentOfNotification() {
        return contentOfNotification;
    }

    public void setContentOfNotification(String contentOfNotification) {
        this.contentOfNotification = contentOfNotification;
    }

    public int getTypeOfNotification() {
        return typeOfNotification;
    }

    public void setTypeOfNotification(int typeOfNotification) {
        this.typeOfNotification = typeOfNotification;
    }

    public int getIdDeadline() {
        return idDeadline;
    }

    public void setIdDeadline(int idDeadline) {
        this.idDeadline = idDeadline;
    }

    public int getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(int idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
