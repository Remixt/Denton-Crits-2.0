package com.cbrant.writers;

import android.text.Editable;

/**
 * Created by cbrant on 5/15/2017.
 */

// class for keeping track of who has how many pages, and if they are an anchor or not.

public class Person {
    private int pages;
    private String name;
    private boolean isAnchorOrange = false;
    private boolean isAnchorBlue = false;

    public Person(String n, Editable text){

    }

    public Person(String n, int p){
        pages = p;
        name = n;
    }

    public boolean isAnchorOrange() {
        return isAnchorOrange;
    }

    public void setAnchorOrange(boolean anchorOrange) {
        isAnchorOrange = anchorOrange;
    }

    public boolean isAnchorBlue() {
        return isAnchorBlue;
    }

    public void setAnchorBlue(boolean anchorBlue) {
        isAnchorBlue = anchorBlue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }





}
