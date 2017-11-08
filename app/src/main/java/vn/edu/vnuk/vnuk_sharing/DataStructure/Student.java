package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by quangngoc430 on 03/11/2017.
 */

public class Student {
    private int idUser;
    private String name;
    private int idClass;
    private ArrayList<Integer> idCoursesArrayList = new ArrayList<Integer>();

    public Student() {
    }
    public Student(int idUser, String name, int idClass, ArrayList<Integer> idCoursesArrayList) {
        this.idUser = idUser;
        this.name = name;
        this.idClass = idClass;
        this.idCoursesArrayList = idCoursesArrayList;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIdClass(){
        return idClass;
    }
    public void setIdClass(int idClass){
        this.idClass = idClass;
    }
    public ArrayList<Integer> getIdCoursesArrayList() {
        return idCoursesArrayList;
    }
    public void setIdCoursesArrayList(ArrayList<Integer> idCoursesArrayList) {
        this.idCoursesArrayList = idCoursesArrayList;
    }

}
