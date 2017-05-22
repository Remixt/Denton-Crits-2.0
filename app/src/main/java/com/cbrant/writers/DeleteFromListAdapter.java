package com.cbrant.writers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cbrant on 5/22/2017.
 */

public class DeleteFromListAdapter extends ArrayAdapter {

    private final Context context;
    private ArrayList<Person> people;
    public ArrayList<Person> getMultiDeleteFlag = new ArrayList<>();

    public DeleteFromListAdapter(Context context, ArrayList<Person> people,ArrayList<String> names) {
        super(context, R.layout.custom_list,names);
        this.context = context;
        this.people = people;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_dialog, parent, false);

        final TextView txtName = (TextView) rowView.findViewById(R.id.textName);
        final TextView txtPageNumber = (TextView) rowView.findViewById(R.id.textPage);
        txtName.setText(people.get(position).getName());
        txtPageNumber.setText(people.get(position).getPages());


        txtName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getMultiDeleteFlag.add(people.get(position));
                txtName.setBackgroundColor(Color.RED);
                txtPageNumber.setBackgroundColor(Color.RED);
            }

        });



        return rowView;
    }

    public ArrayList<Person> getDeleteFlags(){
        return getMultiDeleteFlag;
    }
}