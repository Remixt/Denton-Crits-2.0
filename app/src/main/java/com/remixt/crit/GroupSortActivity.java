package com.remixt.crit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class GroupSortActivity extends AppCompatActivity {
    ListView list;
    DatabaseHandler db;
    ArrayList<Person> people;
    AssignGroupAdapter adapter;
    ArrayList<String> p;
    Button btnShuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sort);
        btnShuffle = findViewById(R.id.btnShuffle);
        list = findViewById(R.id.peopleList);
        db = new DatabaseHandler(this);
        p = new ArrayList<String>();
        people = db.getAllPeople();
        for (int i = 0; i < people.size(); i++)
            p.add(people.get(i).getName());
        adapter = new AssignGroupAdapter(this, people, p);
        list.setAdapter(adapter);
        list.getAdapter().getCount();
        System.out.println("Completed on Create in Group Activity");
    }


    //splits the groups in half and evenly distrubutes people who have pages (as much as possible)
    public void shuffle(View view) {
        adapter.shuffle();
        adapter.notifyDataSetChanged();
        btnShuffle.setVisibility(View.GONE);

    }

    public void reset(View view) {
        for (int i = 0; i < people.size(); i++) {
            people.get(i).setAnchor(0);
            people.get(i).setGroup(0);
        }
        adapter = new AssignGroupAdapter(this, people, p);
        list.setAdapter(adapter);
        list.getAdapter().getCount();
        btnShuffle.setVisibility(View.VISIBLE);
    }
}
