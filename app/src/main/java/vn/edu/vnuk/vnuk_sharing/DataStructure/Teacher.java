package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Teacher{
    private int id;
    private ArrayList<Integer> listOfCourseIds;

    public Teacher(){
        super();
    }
    public Teacher(int id, ArrayList<Integer> listOfCourseIds){
        super();
        this.id = id;
        this.listOfCourseIds = listOfCourseIds;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setListOfCourseIds(ArrayList<Integer> listOfCourseIds) {
        this.listOfCourseIds = listOfCourseIds;
    }
    public ArrayList<Integer> getListOfCourseIds() {
        return listOfCourseIds;
    }

    @Override
    public String toString() {
        String output = "Id : " + this.id;

        int count = 0;

        for (Integer courseId: listOfCourseIds) {
            output += "\n" + "Course " + count + " : " + courseId;
            count++;
        }

        return output;
    }

}