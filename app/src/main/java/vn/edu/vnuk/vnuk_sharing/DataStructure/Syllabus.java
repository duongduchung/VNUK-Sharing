package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

// root/syllabuses/syllabus-[idCourse]

public class Syllabus {
    private int idCourse;
    private String link;
    private String name;
    private long size;
    private boolean exists;

    public Syllabus() {
    }

    public Syllabus(int idCourse, String link, String name, long size, boolean exists) {
        this.idCourse = idCourse;
        this.link = link;
        this.name = name;
        this.size = size;
        this.exists = exists;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
