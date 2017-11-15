package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.HashMap;

// ~/root/notification/notification-[idNotification]

public class Notification {
    private int idNotification;
    private int idCourse;
    private String titleOfNotification;
    private String contentOfNotification;
    private int typeOfNotification;

    public Notification() {
    }

    public Notification(int idNotification, int idCourse, String titleOfNotification, String contentOfNotification, int typeOfNotification) {
        this.idNotification = idNotification;
        this.idCourse = idCourse;
        this.titleOfNotification = titleOfNotification;
        this.contentOfNotification = contentOfNotification;
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
}
