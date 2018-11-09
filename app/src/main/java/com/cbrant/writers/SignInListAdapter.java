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

public class SignInListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Person> people;
    private Sorter sorter = new Sorter();



    public SignInListAdapter(Context context, ArrayList<Person> people, List<String> p) {
        super(context, R.layout.custom_list, p);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Person person = people.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        final TextView txtName = (TextView) rowView.findViewById(R.id.lblName);
        final TextView txtPageNumber = (TextView) rowView.findViewById(R.id.lblPages);
        final ImageView writersLogo = (ImageView) rowView.findViewById(R.id.iconIsAnchor);
        final LinearLayout rowContainer = rowView.findViewById(R.id.groupListItemLayout);
        final ImageView deleteBtn = (ImageView) rowView.findViewById(R.id.btnDelete);
        writersLogo.setVisibility(View.GONE);
        deleteBtn.setVisibility(View.VISIBLE);

        txtName.setText(person.getName());
        txtPageNumber.setText(Integer.toString(person.getPages()));

        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });

        return rowView;
    }
}