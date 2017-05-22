package com.cbrant.writers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.R.id.parent;

public class GroupSortActivity extends AppCompatActivity {
    ListView list;
    Adapter listAdapt;
    DatabaseHandler db;
    ArrayList<String> names;
    ArrayList<String> pages;
    ArrayList<Person> people;
    MySimpleArrayAdapter adapter;
    List<Person> p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sort);
        list = (ListView)findViewById(R.id.peopleList);
        db = new DatabaseHandler(this);
        p = db.getAllPeople();

        names = new ArrayList<String>();
        pages = new ArrayList<String>();
        people = new ArrayList<Person>();
        for (Person person : p) {
            String log = "Id: " + person.getID() + " ,Name: " + person.getName() + " ,Pages: " + person.getPages();
            Log.i("Groupsort ", log);
            names.add(person.getName());
            pages.add(person.getPages());
            people.add(person);
        }
        adapter = new MySimpleArrayAdapter(this, names, pages,people);
        list.setAdapter(adapter);
        list.getAdapter().getCount();
    }


    //splits the groups in half and evenly distrubutes people who have pages (as much as possible)
    public void shuffle(View view){
    people = adapter.shuffle();

        adapter = new MySimpleArrayAdapter(this, names, pages,people);
        list.setAdapter(adapter);
        list.setClickable(false);
    }

    public void reset(View view){
        for(int i = 0; i < people.size(); i++){
            people.get(i).setGroup("White");
        }
        adapter = new MySimpleArrayAdapter(this, names, pages,people);
        list.setAdapter(adapter);
        list.setClickable(true);
    }
}
