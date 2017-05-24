package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class AssignGroupAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Person> people;


    public AssignGroupAdapter(Context context, ArrayList<Person> people, ArrayList<String> names) {
        super(context, R.layout.custom_list,names);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        final TextView txtName = (TextView) rowView.findViewById(R.id.lblName);
        final TextView txtPageNumber = (TextView) rowView.findViewById(R.id.lblPages);
        txtName.setText(people.get(position).getName());
        txtPageNumber.setText(people.get(position).getPages());
        final ImageView writersLogo = (ImageView) rowView.findViewById(R.id.iconIsAnchor);
        writersLogo.setImageResource(android.R.color.transparent);

        if (people.get(position).isOrangeAnchor()) {
            changeAnchorOrange(writersLogo,txtName,txtPageNumber, position);
            writersLogo.setImageResource(R.drawable.anchor);
        } else if (people.get(position).isBlueAnchor()) {
            changeAnchorBlue(writersLogo,txtName,txtPageNumber, position);
            writersLogo.setImageResource(R.drawable.anchor);
        } else if (people.get(position).isOrangeGroup()) {
            changeColorOrange(writersLogo,txtName,txtPageNumber, position);
        } else if (people.get(position).isBlueGroup()) {
            changeColorBlue(writersLogo,txtName,txtPageNumber, position);
        }
        // When a name on the list pressed/clicked make them an anchor and change the color, cycles between orange, blue, and not an anchor (white)
        txtName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (people.get(position).isOrangeAnchor()) {
                    changeAnchorWhite(writersLogo,txtName,txtPageNumber, position);
                    writersLogo.setImageResource(android.R.color.transparent);
                } else if (people.get(position).isBlueAnchor()) {
                    changeAnchorOrange(writersLogo,txtName,txtPageNumber, position);
                    writersLogo.setImageResource(R.drawable.anchor);
                } else {
                    changeAnchorBlue(writersLogo,txtName,txtPageNumber, position);
                    writersLogo.setImageResource(R.drawable.anchor);
                    //writersLogo.setVisibility(View.VISIBLE);
                }
            }

        });


        return rowView;
    }

    private void changeColorBlue(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.CYAN);
        col2.setBackgroundColor(Color.CYAN);
        col3.setBackgroundColor(Color.CYAN);
        people.get(position).setAnchor("White");
        people.get(position).setGroup("Blue");
    }

    private void changeColorOrange(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.rgb(255,127,80));
        col2.setBackgroundColor(Color.rgb(255,127,80));
        col3.setBackgroundColor(Color.rgb(255,127,80));
        people.get(position).setAnchor("White");
        people.get(position).setGroup("Orange");
    }

    private void changeColorWhite(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.TRANSPARENT);
        col2.setBackgroundColor(Color.TRANSPARENT);
        col3.setBackgroundColor(Color.TRANSPARENT);
        people.get(position).setAnchor("White");
        people.get(position).setGroup("White");
    }

    //makes the person an orange anchor
    void changeAnchorOrange(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.rgb(255,127,80));
        col2.setBackgroundColor(Color.rgb(255,127,80));
        col3.setBackgroundColor(Color.rgb(255,127,80));
        people.get(position).setAnchor("Orange");
    }

    //makes the person not an anchor, called it white for continuity
    void changeAnchorWhite(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.TRANSPARENT);
        col2.setBackgroundColor(Color.TRANSPARENT);
        col3.setBackgroundColor(Color.TRANSPARENT);
        people.get(position).setAnchor("White");
    }

    //makes the person a blue anchor
    void changeAnchorBlue(ImageView col1,TextView col2,TextView col3, int position) {
        col1.setBackgroundColor(Color.CYAN);
        col2.setBackgroundColor(Color.CYAN);
        col3.setBackgroundColor(Color.CYAN);
        people.get(position).setAnchor("Blue");
    }


    // Splits people into three groups, anchors, people with pages, and people without pages. Then it evenly(as possible) distributes people with and without pages into two groups.
    public ArrayList<Person> shuffle() {
        int orange = 0;
        int blue = 0;
        int orangeWO = 0;
        int blueWO = 0;
        Collections.shuffle(people);
        for(int i = 0; i < people.size(); i++){
            if(people.get(i).isBlueAnchor()&& people.get(i).getPages().equals("0")){
                blueWO++;
            }else if(people.get(i).isBlueAnchor() ){
                blue++;
            }
            else if(people.get(i).isOrangeAnchor() && people.get(i).getPages().equals("0")){
                orangeWO++;
            }else if (people.get(i).isOrangeAnchor()){
                orange++;
            } else if(people.get(i).getPages().equals("0") && orangeWO >= blueWO){
                people.get(i).setGroup("Blue");
                blueWO++;
            }else if(people.get(i).getPages().equals("0") && orangeWO < blueWO){
                people.get(i).setGroup("Orange");
                orangeWO++;
            }else if(orange >= blue){
                people.get(i).setGroup("Blue");
                blue++;
            }else if(blue > orange){
                people.get(i).setGroup("Orange");
                orange++;
            }
        }

        return people;
    }
}