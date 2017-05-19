package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 */

public class Person {

    //private variables
    int id;
    String name;
    String pages;
    boolean isOrange = false;
    boolean isBlue = false;
    boolean isBlueA = false;
    boolean isOrangeA = false;
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
    public void setAnchor(String color){
        if (color.equals("Blue")){
            isBlueA = true;
            isOrangeA = false;
        }else if(color.equals("Orange")){
            isOrangeA = true;
            isBlueA = false;
            isBlue = false;
            isOrange = false;
        }else{
            isOrangeA = false;
            isBlueA = false;
            isBlue = false;
            isOrange = false;
        }
    }
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
    public boolean isOrangeA() {
        return isOrangeA;
    }
    public boolean isBlueA() {
        return isBlueA;
    }
    public boolean isBlue(){
        return isBlue;
    }
    public boolean isOrange(){
        return isOrange;
    }

}