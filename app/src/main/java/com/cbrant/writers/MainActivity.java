package com.cbrant.writers;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private EditText pagesEdit; // sign in entry form for number of pages
    private EditText namesEdit; // sign in entry form for the member name
    private DatabaseHandler database; // handles all database operations
    ArrayList<String> names;
    ArrayList<String> pages;
    ArrayList<String> displayNameList; // list of the names for each member signed in, used in the display under name and pages form.
    ArrayList<Person> people; // list of people currently signed in, and in the database. Includes their name, id, number of pages, and group status (IE blue group or orange group, anchor ect..)
    ModifiableListAdapter adapter; // adapter that displays a list of members currently in the database, includes action listeners that allow deletion.
    SignInListAdapter signInAdapter;
    List<Person> p;
    ListView list;
    ListView signInList;
    ArrayAdapter<String> signInListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayNameList = new ArrayList<>();
        namesEdit = (EditText) findViewById(R.id.nameField);
        pagesEdit = (EditText) findViewById(R.id.pageField);
        database = new DatabaseHandler(this);
        people = new ArrayList<>();
        signInList = (ListView) findViewById(R.id.signInList);
        signInListAdapter = new SignInListAdapter(this, people, displayNameList);
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
            displayNameList.add(person.getName());
            signInList.setAdapter(signInListAdapter);

            // update the visible sign in list view
            signInListAdapter.notifyDataSetChanged();

            // Let the user know they successfully signed in with a toast notification
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

    //skip the sign in, and grouping activities, move to the timer activity
    public void navigateToTimer(View view) {
        startActivity(new Intent(MainActivity.this, TimerActivity.class));
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
                        database.deleteAll();
                        displayNameList.clear();
                        people.clear();
                        signInListAdapter.notifyDataSetChanged();
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

    // remove a specific entry from the database
    public void removeSingleEntry(View view) {

        p = database.getAllPeople();
        list = new ListView(this);
        names = new ArrayList<>();
        pages = new ArrayList<>();
        people = new ArrayList<>();
        for (Person person : p) {
            names.add(person.getName());
            people.add(person);
        }
        adapter = new ModifiableListAdapter(this, people, names);
        list.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modify")
                .setView(list)
                .setNeutralButton("Done", null)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (adapter.getDeleteFlags() != null) {
                            ArrayList<Person> deleteNames = adapter.getDeleteFlags();
                            for (int i = 0; i < deleteNames.size(); i++) {
                                database.deletePerson(deleteNames.get(i));
                                displayNameList.remove(deleteNames.get(i).getName());
                                people.remove(deleteNames.get(i));
                                signInListAdapter.notifyDataSetChanged();
                                Toast toast = Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // email the sign in list


}