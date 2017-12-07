package vn.edu.vnuk.vnuk_sharing.Database;

public class User_Database {
    int id;
    String username;
    String password;

    public User_Database() {
    }

    public User_Database(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User_Database(String username, String password) {
        this.username = username;
        this.password = password;
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
}
