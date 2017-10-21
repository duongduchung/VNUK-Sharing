package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

public class User {
    private int id;
    private String username;
    private String password;
    private int access; // 0 - teacher, 1 - student

    public User() {
    }

    public User(int id, String username, String password, int access) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}

