package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Teacher{
    private int id;
    private int idUser;
    private String name;

    public Teacher() {
    }

    public Teacher(int id, int idUser, String name) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
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
}