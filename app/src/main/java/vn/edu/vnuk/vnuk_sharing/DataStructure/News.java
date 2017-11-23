package vn.edu.vnuk.vnuk_sharing.DataStructure;

import java.util.Date;

/**
 * Created by quangngoc430 on 23/11/2017.
 */

public class News {

    private int id;
    private Date date;
    private String title;
    private String content;

    public News() {}

    public News(int id, Date date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
