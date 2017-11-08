package vn.edu.vnuk.vnuk_sharing.DataStructure;

import android.os.CpuUsageInfo;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

// root/courses/course-[id]

public class Course {
    private int id;
    private String codeCourse;
    private int idTeacher;
    private int idClass;
    private String name;
    private int status;
    private int deadlinesCount;
    private int announcementsCount;

    public Course() {

    }

    public Course(int id, String codeCourse, int idTeacher, int idClass, String name, int status, int deadlinesCount, int announcementsCount) {
        this.id = id;
        this.codeCourse = codeCourse;
        this.idTeacher = idTeacher;
        this.idClass = idClass;
        this.name = name;
        this.status = status;
        this.deadlinesCount = deadlinesCount;
        this.announcementsCount = announcementsCount;
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

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
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

    public int getDeadlinesCount() {
        return deadlinesCount;
    }

    public void setDeadlinesCount(int deadlinesCount) {
        this.deadlinesCount = deadlinesCount;
    }

    public int getAnnouncementsCount() {
        return announcementsCount;
    }

    public void setAnnouncementsCount(int announcementsCount) {
        this.announcementsCount = announcementsCount;
    }
}
