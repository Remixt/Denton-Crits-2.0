package com.remixt.crit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ArrayList<String> names;
    ArrayList<Person> people; // list of people currently signed in, and in the database. Includes their name, id, number of pages, and group status (IE blue group or orange group, anchor ect..)
    List<Person> p;
    ListView signInList;
    ArrayAdapter<String> signInListAdapter;
    SelectPerson personCallback;

    private EditText pagesEdit; // sign in entry form for number of pages
    private EditText namesEdit; // sign in entry form for the member name
    private Button deleteButton;
    private DatabaseHandler database; // handles all database operations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namesEdit = findViewById(R.id.nameField);
        pagesEdit = findViewById(R.id.pageField);
        deleteButton = findViewById(R.id.btnDelete);

        deleteButton.setVisibility(View.GONE);
        database = new DatabaseHandler(this);
        people = new ArrayList<>();
        people = database.getAllPeople();

        names = new ArrayList<>();
        if (!people.isEmpty()) {
            for (int i = 0; i < people.size(); i++) {
                names.add(people.get(i).getName());
            }
        }

        personCallback = new SelectPerson() {
            @Override
            public void personSelected() {
                deleteButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void noPersonSelected() {
                deleteButton.setVisibility(View.GONE);
            }
        };


        signInList = findViewById(R.id.signInList);
        signInListAdapter = new SignInListAdapter(this, people, names, personCallback);
        signInList.setAdapter(signInListAdapter);

        clearDatabase();
    }
    //records the values entered for name and pages, creates a new instance of person class to keep track.
    public void signIn(View view) {
        //ensure the user filled out the require forms
        if (namesEdit.getText().length() < 1 || pagesEdit.getText().length() < 1) {

            new AlertDialog.Builder(this)
                    .setMessage("Make sure you fill out your name and how many pages!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {

            Person person = new Person(namesEdit.getText().toString(), Integer.parseInt(pagesEdit.getText().toString()));
            database.addPerson(person);
            people.add(person);
            names.add(person.getName());
            // update the visible sign in list view
            signInListAdapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully Signed In!", Toast.LENGTH_SHORT);
            toast.show();

            // hide the  keyboard when sign in button is pressed
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(namesEdit.getWindowToken(), 0);

            // reset the sign in form to allow others to sign in
            namesEdit.setText("");
            pagesEdit.setText("");
        }
    }
    //starts the group sorting activity
    public void navigateToGroupSort(View view) {
        startActivity(new Intent(MainActivity.this, GroupSortActivity.class));
    }
    //empty the database
    public void clearDatabase(View view) {
        //make sure they hit the button on purpose.
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        people.clear();
                        names.clear();
                        database.deleteAll();
                        signInListAdapter.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Clear Existing Data?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void clearDatabase() {
        //make sure they hit the button on purpose.
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        people.clear();
                        names.clear();
                        database.deleteAll();
                        signInListAdapter.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Clear Existing Data?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    // remove a specific entry from the database
    public void removeSingleEntry(View view) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isSelected()) {
                database.deletePerson(people.get(i));
                names.remove(i);
                people.remove(i);
            }
        }
        deleteButton.setVisibility(View.GONE);
        signInListAdapter.notifyDataSetChanged();
    }
}