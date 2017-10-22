package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Class {
    private int id;
    private String name;

    public Class() {
    }

    public Class(int id, int idCourse, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
