package vn.edu.vnuk.vnuk_sharing.DataStructure;

import android.os.CpuUsageInfo;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Course {
    private int id;
    private String codeCourse;
    private int idTeacher;
    private int idClassOfCourse;
    private String name;
    private int status;
    private ArrayList<Integer> deadlineIdArrayList;
    private ArrayList<Integer> annoucementIdArrayList;

    public Course() {

    }

    public Course(int id, String codeCourse, int idTeacher, int idClassOfCourse, String name, int status, ArrayList<Integer> deadlineIdArrayList, ArrayList<Integer> annoucementIdArrayList) {
        this.id = id;
        this.codeCourse = codeCourse;
        this.idTeacher = idTeacher;
        this.idClassOfCourse = idClassOfCourse;
        this.name = name;
        this.status = status;
        this.deadlineIdArrayList = deadlineIdArrayList;
        this.annoucementIdArrayList = annoucementIdArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeCourse() {
        return codeCourse;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdClassOfCourse() {
        return idClassOfCourse;
    }

    public void setIdClassOfCourse(int idClassOfCourse) {
        this.idClassOfCourse = idClassOfCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Integer> getDeadlineIdArrayList() {
        return deadlineIdArrayList;
    }

    public void setDeadlineIdArrayList(ArrayList<Integer> deadlineIdArrayList) {
        this.deadlineIdArrayList = deadlineIdArrayList;
    }

    public ArrayList<Integer> getAnnoucementIdArrayList() {
        return annoucementIdArrayList;
    }

    public void setAnnoucementIdArrayList(ArrayList<Integer> annoucementIdArrayList) {
        this.annoucementIdArrayList = annoucementIdArrayList;
    }

}
