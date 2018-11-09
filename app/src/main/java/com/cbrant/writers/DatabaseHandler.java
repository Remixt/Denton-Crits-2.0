package com.cbrant.writers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "DentonWriters"; // Database Name
    private static final String TABLE_SIGNIN = "DentonWritersSignIn"; // Table Name

    // Table database columns
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String PAGES = "PAGES";

    // Default constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override // Creating tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SIGNIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + PAGES + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNIN);
        // Create tables again
        onCreate(db);
    }

    // Add new person to the database
    void addPerson(Person person) {
        System.err.println("Adding " + person.getName() + " to database!");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName()); // Person Name
        values.put(PAGES, person.getPages()); // Person Pages
        // Inserting Row
        db.insert(TABLE_SIGNIN, null, values);
        db.close(); // Closing database connection
    }

    // Search the database to see if a person already exists.
    Boolean searchFor(Person person) {
        boolean exists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SIGNIN, new String[]{KEY_ID, KEY_NAME, PAGES}, KEY_ID + "=?",
                new String[]{Integer.toString(person.getId())}, null, null, null, null);
        if (cursor != null) {
            exists = true;
            cursor.moveToFirst();
        }
        return exists;
    }

    // Get all people in the database
    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SIGNIN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setPages(cursor.getInt(2));
                // Adding person to list
                personList.add(person);
                System.err.println("Found " + person.getName() + " in database!" );
            } while (cursor.moveToNext());
        }
        // return person list
        return personList;
    }

    // Update existing person in the database
    public int updatePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(PAGES, person.getPages());

        // updating row
        return db.update(TABLE_SIGNIN, values, KEY_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
    }

    // Deleting every entry in the database
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SIGNIN, null, null);
        db.close();
    }

    // Delete a single person from the database
    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SIGNIN, KEY_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        db.close();
    }
}