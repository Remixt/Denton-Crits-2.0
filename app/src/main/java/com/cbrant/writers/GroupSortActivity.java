package com.cbrant.writers;

import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GroupSortActivity extends AppCompatActivity {
    ListView list;
    Adapter listAdapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sort);
        list = (ListView)findViewById(R.id.peopleList);

    }

}
