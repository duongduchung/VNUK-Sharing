package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.Date;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

// root/announcements/announcement-[idCourse]-[id]

public class Announcement {
    private int id;
    private int idCourse;
    private String title;
    private Date date;
    private String description;

    public Announcement() {
    }

    public Announcement(int id, int idCourse, String title, Date date, String description) {
        this.id = id;
        this.idCourse = idCourse;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
