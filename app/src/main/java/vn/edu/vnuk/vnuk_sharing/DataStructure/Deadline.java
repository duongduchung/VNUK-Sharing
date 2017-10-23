package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.Date;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

// root/deadlines/deadline-[idCourse]-[id]

public class Deadline {
    private int id;
    private int idCourse;
    private String title;
    private String description;
    private Date date;

    public Deadline() {
    }

    public Deadline(int id, int idCourse, String title, String description, Date date) {
        this.id = id;
        this.idCourse = idCourse;
        this.title = title;
        this.description = description;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
