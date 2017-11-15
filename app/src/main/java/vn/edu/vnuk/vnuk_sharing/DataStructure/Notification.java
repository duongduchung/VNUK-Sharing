package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.HashMap;

/**
 * Created by quangngoc430 on 15/11/2017.
 */

public class Notification {
    private final HashMap<Integer, String> NOTIFICATION_TYPE = new HashMap<Integer, String>();
    private int idNotification;
    private int idCourse;
    private String titleOfNotification;
    private String contentOfNotification;
    private int typeOfNotification;

    private void generateNotificationType(){
        NOTIFICATION_TYPE.put(0, "Create syllabus");
        NOTIFICATION_TYPE.put(1, "Update syllabus");
        NOTIFICATION_TYPE.put(2, "Add announcement");
        NOTIFICATION_TYPE.put(3, "Add deadline");
    }

    public Notification(){
        generateNotificationType();
    }

    public Notification(int idNotification, int idCourse, String titleOfNotification, String contentOfNotification, int typeOfNotification) {
        generateNotificationType();
        this.idNotification = idNotification;
        this.idCourse = idCourse;
        this.titleOfNotification = titleOfNotification;
        this.contentOfNotification = contentOfNotification;
        this.typeOfNotification = typeOfNotification;
    }

    public HashMap<Integer, String> getNOTIFICATION_TYPE() {
        return NOTIFICATION_TYPE;
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
