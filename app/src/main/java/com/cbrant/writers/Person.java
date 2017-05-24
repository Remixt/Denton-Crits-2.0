package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 * This class defines the person object, which allows tracking of users that are signed in to the Denton Writers Group.
 */

public class Person {

    //private variables
    private int id; // unique identifier, for allowing duplicates and searches.
    private String name; // member name
    private String pages; // number of pages member has, using string for easy database writing.
    private boolean isOrange = false;  // is in orange group
    private boolean isBlue = false; // is in blue group
    private boolean isBlueA = false; // is a blue anchor
    private boolean isOrangeA = false; // is an orange anchor

    // Empty constructor
    public Person() {

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
    // getting number of pages
    public String getPages() {
        return this.pages;
    }
    // setting number of pages
    public void setPages(String pages) {
        this.pages = pages;
    }
    //set if the person is an orange anchor, blue anchor, or not an anchor.
    public void setAnchor(String color){
        if (color.equals("Blue")){
            isBlueA = true;
            isOrangeA = false;
            isOrange = false;
            isBlue = false;
        }else if(color.equals("Orange")){
            isOrangeA = true;
            isBlueA = false;
            isBlue = false;
            isOrange = false;
        }else{
            isOrangeA = false;
            isBlueA = false;
        }
    }
    //set which group the person is in, sets other group and anchor booleans to false since you can only be one.
    public void setGroup(String color){
        if (color.equals("Blue")){
            isBlue = true;
            isOrange = false;
            isOrangeA = false;
            isBlueA = false;
        }else if(color.equals("Orange")){
            isOrange = true;
            isBlue = false;
            isOrangeA = false;
            isBlueA = false;
        }else{
            isOrange = false;
            isBlue = false;
            isOrangeA = false;
            isBlueA = false;
        }
    }
    //check if orange anchor
    public boolean isOrangeAnchor() {
        return isOrangeA;
    }
    //check if orange group
    public boolean isOrangeGroup(){
        return isOrange;
    }
    //check if blue anchor
    public boolean isBlueAnchor() {
        return isBlueA;
    }
    //check if blue group
    public boolean isBlueGroup(){
        return isBlue;
    }


}