package com.cbrant.writers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.R.id.parent;

public class GroupSortActivity extends AppCompatActivity {
    ListView list;
    Adapter listAdapt;
    DatabaseHandler db;
    ArrayList<String> names;
    ArrayList<String> pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sort);
        list = (ListView)findViewById(R.id.peopleList);
        db = new DatabaseHandler(this);
        List<Person> p = db.getAllPeople();

        names = new ArrayList<String>();
        pages = new ArrayList<String>();
        for (Person person : p) {
            String log = "Id: " + person.getID() + " ,Name: " + person.getName() + " ,Pages: " + person.getPages();
            Log.i("Groupsort ", log);
            names.add(person.getName());
            pages.add(person.getPages());
        }
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, names, pages);
        list.setAdapter(adapter);

    }


}
