package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.Date;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Annoucement {
    private int id;
    private Date dateTime;
    private String description;

    public Annoucement(){
        super();
    }
    public Annoucement(int id, Date dateTime, String description){
        super();
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setDateTime(Date dateTime){
        this.dateTime = dateTime;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Id : " + this.id + "\n"
                + "Date time : " + this.dateTime + "\n"
                + "Description : " + this.description;
    }
}
