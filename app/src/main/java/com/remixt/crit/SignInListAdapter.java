package com.remixt.crit;

/**
 * Created by cbrant on 5/17/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SignInListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Person> people;
    private SelectPerson callback;
    private int numSelected = 0;

    public SignInListAdapter(Context context, ArrayList<Person> people, List<String> p, SelectPerson c) {
        super(context, R.layout.custom_list, p);
        this.context = context;
        this.people = people;
        callback = c;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);


        final Person person = people.get(position);
        final TextView txtName = rowView.findViewById(R.id.lblName);
        final TextView txtPageNumber = rowView.findViewById(R.id.lblPages);
        final LinearLayout rowContainer = rowView.findViewById(R.id.groupListItemLayout);


        txtName.setText(person.getName());
        txtPageNumber.setText(Integer.toString(person.getPages()));

        rowContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (person.isSelected()) {
                    person.setSelected(false);
                    rowContainer.setBackgroundColor(Color.TRANSPARENT);
                    numSelected--;
                } else {
                    person.setSelected(true);
                    rowContainer.setBackgroundColor(Color.argb(50, 140, 140, 140));
                    numSelected++;
                }
                switch (numSelected) {
                    case 0:
                        callback.noPersonSelected();
                        break;
                    default:
                        callback.personSelected();
                }
            }

        });
        rowContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        return rowView;
    }

}