package com.cbrant.writers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Sorter {
    ArrayList<Person> pmap = new ArrayList<Person>();
    ArrayList<Person> amap = new ArrayList<Person>();
    ArrayList<Person> npages = new ArrayList<Person>();
    int groupOneCount = 0;
    int groupOnePages = 0;
    int groupTwoCount = 0;
    int groupTwoPages = 0;
    private int alterGroupNum = 1;

    public ArrayList<Person> sort(ArrayList<Person> people){
        for(int i = 0; i < people.size(); i++){
            groupOneCount = 0;
            groupTwoCount = 0;
            groupTwoPages = 0;
            groupOnePages = 0;
            if(people.get(i).getAnchor() > 0){ // person is an anchor
                addGroupPages(people.get(i).getGroup(),people.get(i).getPages());
                addToGroupCount(people.get(i).getGroup());
                amap.add(people.get(i));
            }else if(people.get(i).getPages() == 0){
                npages.add(people.get(i));
            }else{
                pmap.add(people.get(i));
            }
        }
        ArrayList<Person> p = new ArrayList<Person>();


        while(!pmap.isEmpty()){
            Random rand = new Random();
            int key = rand.nextInt(pmap.size());
            Person person = pmap.get(key);
            int temp = person.getPages();
            //fewer people in group 1
            if(groupOnePages < groupTwoPages + 2){ // try to keep the groups within 2 pages if possible
                if(groupOneCount > groupTwoCount + 1){ // don't separate group numbers by more than 2 people at any given time
                    person.setGroup(2);
                    addToGroupCount(2);
                    addGroupPages(2,person.getPages());
                    alterGroupNum = 1;
                }else {
                    person.setGroup(1);
                    addToGroupCount(1);
                    addGroupPages(1, person.getPages());
                    alterGroupNum = 2;
                }
            } else if (groupTwoPages < groupOnePages + 2){
                if(groupTwoCount > groupOneCount + 1){
                    person.setGroup(1);
                    addToGroupCount(1);
                    addGroupPages(1, person.getPages());
                    alterGroupNum = 2;
                }else{
                    person.setGroup(2);
                    addToGroupCount(2);
                    addGroupPages(2,person.getPages());
                    alterGroupNum = 1;
                }

            }
            else {
                person.setGroup(alterGroupNum);
                addToGroupCount(alterGroupNum);
                addGroupPages(alterGroupNum,person.getPages());
                switch(alterGroupNum){
                    case 1: alterGroupNum = 2;
                    case 2: alterGroupNum = 1;
                }
            }

            p.add(person);
            pmap.remove(key);
        }
        for(int i = 0; i < amap.size(); i++){
            p.add(amap.get(i));
        }
        while(!npages.isEmpty()){
            Random rand = new Random();
            int key = rand.nextInt(npages.size());
            if(groupOneCount <= groupTwoCount){
                npages.get(key).setGroup(1);
                addToGroupCount(1);
                p.add(npages.get(key));
                npages.remove(key);
            }else{
                npages.get(key).setGroup(2);
                addToGroupCount(2);
                p.add(npages.get(key));
                npages.remove(key);
            }
        }
        for(int i = 0; i < p.size(); i++){
            System.err.println("Name: "+ p.get(i).getName()+" Group: "+p.get(i).getGroup() + " Pages: " + p.get(i).getPages());
        }
        System.err.println("Group 1 pages: " + groupOnePages);
        System.err.println("Group 2 pages: " + groupTwoPages);
        System.err.println("Group 1 count: " + groupOneCount);
        System.err.println("Group 2 count: " + groupTwoCount);

        return p;
    }
    private void addToGroupCount(int groupNum){
        switch(groupNum){
            case 1: groupOneCount++;
                break;
            default: groupTwoCount++;
        }
    }
    private void addGroupPages(int groupNum, int numPages){
        switch(groupNum){
            case 1: groupOnePages += numPages;
                break;
            default: groupTwoPages += numPages;
        }
    }
}