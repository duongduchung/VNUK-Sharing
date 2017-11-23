package vn.edu.vnuk.vnuk_sharing.DataStructure;

/**
 * Created by Quangngoc430 on 10/20/2017.
 */

// root/users/user-[username]-[password]

public class User {
    private int id;
    private String username;
    private String password;
    private int access; // 0 - teacher, 1 - student
    private Setting setting;

    public User() {
    }

    public User(int id, String username, String password, int access, Setting setting) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.access = access;
        this.setting = setting;
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

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}

