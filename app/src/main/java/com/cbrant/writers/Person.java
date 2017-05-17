package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 */

public class Person {

    //private variables
    int id;
    String name;
    String pages;

    // Empty constructor
    public Person() {

    }

    // constructor
    public Person(int id, String name, String pages) {
        this.id = id;
        this.name = name;
        this.pages = pages;
    }

    // constructor
    public Person(String name, String pages) {
        this.name = name;
        this.pages = pages;
    }

    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting name
    public String getName() {
        return this.name;
    }

    // setting name
    public void setName(String name) {
        this.name = name;
    }

    // getting phone number
    public String getPages() {
        return this.pages;
    }

    // setting phone number
    public void setPages(String pages) {
        this.pages = pages;
    }
}