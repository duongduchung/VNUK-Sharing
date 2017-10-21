package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Syllabus {
    private int idCourse;
    private String link;
    private String name;
    private int size;

    public Syllabus() {
    }

    public Syllabus(int idCourse, String link, String name, int size) {
        this.idCourse = idCourse;
        this.link = link;
        this.name = name;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
