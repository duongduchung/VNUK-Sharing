package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Teacher{
    private int id;
    private int idUser;
    private String name;
    private ArrayList<Integer> idCoursesArrayList = new ArrayList<Integer>();

    public Teacher() {
    }

    public Teacher(int id, int idUser, String name, ArrayList<Integer> idCoursesArrayList) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.idCoursesArrayList = idCoursesArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Integer> getIdCoursesArrayList(){
        return idCoursesArrayList;
    }

    public void setIdCoursesArrayList(ArrayList<Integer> idCoursesArrayList){
        this.idCoursesArrayList = idCoursesArrayList;
    }
}














