package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Person> people;
    private String cc = "n";

    public MySimpleArrayAdapter(Context context, ArrayList<String> values, ArrayList<String> values1, ArrayList<Person> people) {
        super(context, R.layout.custom_list, values);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        final TextView txtName = (TextView) rowView.findViewById(R.id.lblPage);
        final TextView txtPageNumber = (TextView) rowView.findViewById(R.id.lblName);
        txtName.setText(people.get(position).getName());
        txtPageNumber.setText(people.get(position).getPages());
        ImageView writersLogo = (ImageView) rowView.findViewById(R.id.iconIsAnchor);
        writersLogo.setVisibility(View.INVISIBLE);
        final int p = position;

        if (people.get(position).isOrangeA()) {
            changeAnchorOrange(txtName, position);
            writersLogo.setVisibility(View.VISIBLE);
        } else if (people.get(position).isBlueA()) {
            changeAnchorBlue(txtName, position);
            writersLogo.setVisibility(View.VISIBLE);
        } else if (people.get(position).isOrange()) {
            changeColorOrange(txtName, position);
        } else if (people.get(position).isBlue()) {
            changeColorBlue(txtName, position);
        }
        // When a name on the list pressed/clicked make them an anchor and change the color, cycles between orange, blue, and not an anchor (white)
        txtName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (people.get(p).isOrangeA()) {
                    changeAnchorWhite(txtName, position);

                } else if (people.get(p).isBlueA()) {
                    changeAnchorOrange(txtName, position);
                } else {
                    changeAnchorBlue(txtName, position);
                }
            }

        });


        return rowView;
    }

    private void changeColorBlue(TextView row, int position) {
        row.setBackgroundColor(Color.CYAN);
        people.get(position).setAnchor("White");
        people.get(position).setGroup("Blue");
    }

    private void changeColorOrange(TextView row, int position) {
        row.setBackgroundColor(Color.rgb(255,127,80));
        people.get(position).setAnchor("White");
        people.get(position).setGroup("Orange");
    }

    private void changeColorWhite(TextView row, int position) {
        row.setBackgroundColor(Color.TRANSPARENT);
        people.get(position).setAnchor("White");
        people.get(position).setGroup("White");
    }

    //makes the person an orange anchor
    void changeAnchorOrange(TextView row, int position) {
        row.setBackgroundColor(Color.rgb(255,127,80));
        people.get(position).setAnchor("Orange");
    }

    //makes the person not an anchor, called it white for continuity
    void changeAnchorWhite(TextView row, int position) {
        row.setBackgroundColor(Color.TRANSPARENT);
        people.get(position).setAnchor("White");
    }

    //makes the person a blue anchor
    void changeAnchorBlue(TextView row, int position) {
        row.setBackgroundColor(Color.CYAN);
        people.get(position).setAnchor("Blue");
    }


    // Splits people into three groups, anchors, people with pages, and people without pages. Then it evenly(as possible) distributes people with and without pages into two groups.
    public ArrayList<Person> shuffle() {

        ArrayList<Person> haveNoPages = new ArrayList<>(); // people who don't have pages, including anchors.
        ArrayList<Person> anchorList = new ArrayList<>(); // people who are Anchors.

        int blueSize = 0 , orangeSize = 0;

        // Split people who don't have pages
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).getPages().equals("0")){
                haveNoPages.add(people.get(i));
                people.remove(i);
                i--;
            }
        }

        // holds the anchors temporarily
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isBlueA() || people.get(i).isOrangeA()) {
                if(people.get(i).isBlueA()){
                    blueSize ++;
                } else {
                    orangeSize++;
                }
                anchorList.add(people.get(i));
                people.remove(i);
                i--;
            }
        }

        // Randomize the lists to "draw" names.
        Collections.shuffle(people);
        Collections.shuffle(haveNoPages);

        // Alternate group placement until the list is empty.
        for (int i = 0; i < people.size(); i++) {
            if (i % 2 == 0) {
                people.get(i).setGroup("Orange");
            } else {
                people.get(i).setGroup("Blue");
            }
        }

        // Alternate group placement until the list is empty.
        for (int i = 0; i < haveNoPages.size(); i++) {
            if (i % 2 == 0) {
                haveNoPages.get(i).setGroup("Orange");
            } else {
                haveNoPages.get(i).setGroup("Blue");
            }
        }

        people.addAll(anchorList);
        people.addAll(haveNoPages);
        anchorList.clear();
        haveNoPages.clear();
        return people;
    }
}