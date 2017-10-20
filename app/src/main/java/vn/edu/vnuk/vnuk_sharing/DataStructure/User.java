package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class User {
    private int id;
    private String username;
    private String password;
    private int access; // 0 - teacher, 1 - student

    public User(){
        super();
    }
    public User(int id, String username, String password, int access){
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setAccess(int access){
        this.access = access;
    }
    public int getAccess(){
        return this.access;
    }

    @Override
    public String toString(){
        return "Id : " + this.id + "\n"
                + "Username : " + this.username + "\n"
                + "Password : " + this.password + "\n"
                + "Access : " + this.access;
    }
}

