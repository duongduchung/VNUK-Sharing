package vn.edu.vnuk.vnuk_sharing.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "vnuksharing";

    private static final String TABLE_USERS = "users";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    public void onDrop(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // Adding new userDatabase
    public void addUser(User_Database userDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, userDatabase.getUsername()); // User_Database username
        values.put(KEY_PASSWORD, userDatabase.getPassword()); // User_Database password

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single user
    public User_Database getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_USERNAME, KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User_Database userDatabase = new User_Database(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return userDatabase
        return userDatabase;
    }

    // Getting All Users
    public List<User_Database> getAllUsers() {
        List<User_Database> userDatabaseList = new ArrayList<User_Database>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User_Database userDatabase = new User_Database();
                userDatabase.setId(Integer.parseInt(cursor.getString(0)));
                userDatabase.setUsername(cursor.getString(1));
                userDatabase.setPassword(cursor.getString(2));
                // Adding userDatabase to list
                userDatabaseList.add(userDatabase);
            } while (cursor.moveToNext());
        }

        // return user list
        return userDatabaseList;
    }

    // Updating single userDatabase
    public int updateUser(User_Database userDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, userDatabase.getUsername());
        values.put(KEY_PASSWORD, userDatabase.getPassword());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(userDatabase.getId()) });
    }

    // Deleting single userDatabase
    public void deleteUser(User_Database userDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(userDatabase.getId()) });
        db.close();
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
