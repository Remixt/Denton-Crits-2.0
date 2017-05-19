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
    private final ArrayList<String> values;
    private final ArrayList<String> values1;
    private ArrayList<Person> people;
    private String cc = "n";

    public MySimpleArrayAdapter(Context context, ArrayList<String> values, ArrayList<String> values1, ArrayList<Person> people) {
        super(context, R.layout.custom_list, values);
        this.context = context;
        this.values = values;
        this.values1 = values1;
        this.people = people;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        final TextView textView = (TextView) rowView.findViewById(R.id.lblPage);
        TextView textView1 = (TextView) rowView.findViewById(R.id.lblName);
        textView.setText(people.get(position).getName());
        textView1.setText(people.get(position).getPages());
        ImageView img = (ImageView) rowView.findViewById(R.id.iconIsAnchor);
        img.setVisibility(View.INVISIBLE);
        final int p = position;

        if (people.get(position).isOrangeA()) {
            changeAnchorOrange(textView, position);
            img.setVisibility(View.VISIBLE);
        } else if (people.get(position).isBlueA()) {
            changeAnchorBlue(textView, position);
            img.setVisibility(View.VISIBLE);
        } else if (people.get(position).isOrange()) {
            changeColorOrange(textView, position);
        } else if (people.get(position).isBlue()) {
            changeColorBlue(textView, position);
        }
        // When a name on the list pressed/clicked make them an anchor and change the color, cycles between orange, blue, and not an anchor (white)
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (people.get(p).isOrangeA()) {
                    changeAnchorWhite(textView, position);

                } else if (people.get(p).isBlueA()) {
                    changeAnchorOrange(textView, position);
                } else {
                    changeAnchorBlue(textView, position);
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

    // removes anchors from the list and then evenly sorts the list into blue and orange groups
    public ArrayList<Person> shuffle() {
        ArrayList<Person> temp = new ArrayList<>();
        // holds the anchors temporarily
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isBlueA() || people.get(i).isOrangeA()) {
                temp.add(people.get(i));
                people.remove(i);

            }
        }
        Collections.shuffle(people);

        for (int i = 0; i < people.size(); i++) {
            if (i % 2 == 0) {
                people.get(i).setGroup("Orange");
            } else {
                people.get(i).setGroup("Blue");
            }
        }
        people.addAll(temp);
        temp.clear();
        return people;
    }

}