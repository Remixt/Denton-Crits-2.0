package com.cbrant.writers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private EditText pEdit;
    private EditText nEdit;
    private Button btnSignIn;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nEdit = (EditText) findViewById(R.id.nameField);
        pEdit = (EditText) findViewById(R.id.pageField);
        btnSignIn = (Button) findViewById(R.id.btnSignin);
        db = new DatabaseHandler(this);
    }

    //records the values entered for name and pages, creates a new instance of person class to keep track.
    public void signIn(View view) {
        if (nEdit.getText().length() > 0 && pEdit.getText().length() > 0) {
            db.addPerson(new Person(nEdit.getText().toString(), pEdit.getText().toString()));
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
                    .setMessage("Make sure you fill out your name and how many pages!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    //starts the group sorting activity after everyone has signed in.
    public void startGroup(View view) {
        startActivity(new Intent(MainActivity.this, GroupSortActivity.class));
    }
}