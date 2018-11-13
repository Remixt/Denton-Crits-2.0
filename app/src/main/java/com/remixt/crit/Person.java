package com.remixt.crit;

/**
 * Created by cbrant on 5/17/2017.
 * This class defines the person object, which allows tracking of users that are signed in to the Denton Writers Group.
 */

public class Person {
    private String name;
    private int group = 0;
    private int anchor = 0;
    private int pages = 0;
    private int id = 0;
    private boolean selected = false;

    public Person() {
    }

    public Person(String n) {
        name = n;
    }

    public Person(String n, int p) {
        name = n;
        pages = p;
    }

    public Person(String n, int a, int p) {
        name = n;
        anchor = a;
        pages = p;
        group = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getAnchor() {
        return anchor;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}