package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.ArrayList;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Teacher{
    private int id;
    private ArrayList<String> listOfCourseCodes;

    public Teacher(){
        super();
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setListOfCourseCodes(ArrayList<String> listOfCourseCodes) {
        this.listOfCourseCodes = listOfCourseCodes;
    }
    public ArrayList<String> getListOfCourseCodes() {
        return listOfCourseCodes;
    }

    @Override
    public String toString() {
        String output = "Id : " + this.id;

        int count = 0;

        for (String courseCode: listOfCourseCodes) {
            output += "\n" + "Course " + count + " : " + courseCode;
            count++;
        }

        return output;
    }

}