package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class Syllabus {
    private String link;
    private String name;
    private int size;

    public Syllabus(){
        super();
    }
    public Syllabus(String link, String name, int size){
        super();
        this.link = link;
        this.name = name;
        this.size = size;
    }

    public void setLink(String link){
        this.link = link;
    }
    public String getLink() {
        return link;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Link : " + this.link + "\n"
                + "Name : " + this.name + "\n"
                + "Size : " + this.size;
    }
}
