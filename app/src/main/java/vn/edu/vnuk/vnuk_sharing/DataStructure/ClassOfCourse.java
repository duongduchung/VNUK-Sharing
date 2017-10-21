package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class ClassOfCourse {
    private int id;
    private String name;

    public ClassOfCourse(){
        super();
    }
    public ClassOfCourse(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id : " + this.id + "\n"
                + "Name : " + this.name;
    }

}
