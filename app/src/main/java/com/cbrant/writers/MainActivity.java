package com.cbrant.writers;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {


    private EditText pEdit;
    private EditText nEdit;
    private Button btnSignIn;
    private DatabaseHandler db;
    ArrayList<String> names;
    ArrayList<String> pages;
    ArrayList<Person> people;
    DeleteFromListAdapter adapter;
    List<Person> p;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nEdit = (EditText) findViewById(R.id.nameField);
        pEdit = (EditText) findViewById(R.id.pageField);
        btnSignIn = (Button) findViewById(R.id.btnSignin);
        db = new DatabaseHandler(this);
        people = new ArrayList<>();
    }

    public Boolean isDupName(String name) {
        for (Person p : people) {
            if (p.getName().equals(name))
                return true;
        }
        return false;
    }

    //records the values entered for name and pages, creates a new instance of person class to keep track.
    public void signIn(View view) {
        if (nEdit.getText().length() < 1 || pEdit.getText().length() < 1) {

                new AlertDialog.Builder(this)
                        .setMessage("Make sure you fill out your name and how many pages!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else if (isDupName(nEdit.getText().toString()) == false) {
                Person person = new Person(nEdit.getText().toString(), pEdit.getText().toString());
                db.addPerson(person);
                people.add(person);
                nEdit.setText("");
                pEdit.setText("");

                new AlertDialog.Builder(this)
                        .setMessage("Success!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("There is already a person with that name. Please choose a different name.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                nEdit.setText("");
            }

        }

    //starts the group sorting activity after everyone has signed in.
    public void startGroup(View view) {
        startActivity(new Intent(MainActivity.this, GroupSortActivity.class));
    }
    public void skipToTimer(View view) {
        startActivity(new Intent(MainActivity.this, TimerActivity.class));
    }
    //empty the database
    public void clearTable(View view){
        //make sure they hit the button on purpose.
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        db.deleteAll();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete all the names?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }

    public void removeSingle(View view){


        p = db.getAllPeople();
        list = new ListView(this);
        names = new ArrayList<String>();
        pages = new ArrayList<String>();
        people = new ArrayList<Person>();
        for (Person person : p) {
            names.add(person.getName());
            people.add(person);
        }
        adapter = new DeleteFromListAdapter(this, people,names);
        list.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Names")
                .setView(list)
                .setNeutralButton("Done", null)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(adapter.getDeleteFlags() != null){
                            ArrayList<Person> deleteNames = new ArrayList<>();
                            deleteNames = adapter.getDeleteFlags();
                            for(int i = 0; i < deleteNames.size(); i++){
                                db.deleteContact(deleteNames.get(i));

                            }
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}