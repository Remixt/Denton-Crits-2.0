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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignGroupAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Person> people;
    private Sorter sorter = new Sorter();



    public AssignGroupAdapter(Context context, ArrayList<Person> people, List<String> p) {
        super(context, R.layout.custom_list, p);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Person person = people.get(position);
        System.err.println("Inside get view method...");
        
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        final TextView txtName = (TextView) rowView.findViewById(R.id.lblName);
        final TextView txtPageNumber = (TextView) rowView.findViewById(R.id.lblPages);
        final ImageView writersLogo = (ImageView) rowView.findViewById(R.id.iconIsAnchor);
        final LinearLayout rowContainer = rowView.findViewById(R.id.groupListItemLayout);
        final ImageButton deleteBtn = rowView.findViewById(R.id.btnDelete);
        deleteBtn.setVisibility(View.GONE);

        txtName.setText(person.getName());
        txtPageNumber.setText(Integer.toString(person.getPages()));

        switch(person.getGroup()){
            case 1:
                rowContainer.setBackgroundColor(Color.CYAN);
                break;
            case 2:
                rowContainer.setBackgroundColor(Color.rgb(255, 127, 80));
                break;
            default:
                rowContainer.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
        switch(person.getAnchor()){
            case 1:
                writersLogo.setImageResource(R.drawable.anchor);
                break;
            default:
                writersLogo.setVisibility(View.INVISIBLE);
                break;
        }
        rowContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(person.getAnchor() == 0 && person.getGroup() == 0) { // Has no group and is not an anchor
                    person.setAnchor(1);
                    person.setGroup(1);
                    rowContainer.setBackgroundColor(Color.CYAN);
                    writersLogo.setVisibility(View.VISIBLE);
                    people.set(position,person);
                }else if(person.getAnchor() == 1 && person.getGroup() == 1){ // Is in group 1 and is an anchor
                    person.setGroup(2);
                    rowContainer.setBackgroundColor(Color.rgb(255, 127, 80));
                    people.set(position,person);
                }else{
                    person.setAnchor(0);
                    person.setGroup(0);
                    rowContainer.setBackgroundColor(Color.TRANSPARENT);
                    writersLogo.setVisibility(View.INVISIBLE);
                    people.set(position,person);
                }
            }

        });

        return rowView;
    }

    public void shuffle(){
        sorter.sort(people);
    }
}