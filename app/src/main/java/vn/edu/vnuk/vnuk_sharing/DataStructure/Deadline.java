package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.Date;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Deadline {
    private int id;
    private String dateTime;
    private String description;

    public Deadline(){
        super();
    }
    public Deadline(int id, String dateTime, String description){
        super();
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }
    public String getDateTime(){
        return this.dateTime;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString(){
        return "Id : " + this.id + "\n"
                + "Date time : " + this.dateTime + "\n"
                + "Description : " + this.description;
    }
}
