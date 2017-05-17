package com.cbrant.writers;

/**
 * Created by cbrant on 5/17/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private final ArrayList<String> values1;

    public MySimpleArrayAdapter(Context context, ArrayList<String> values, ArrayList<String> values1) {
        super(context, R.layout.custom_list, values);
        this.context = context;
        this.values = values;
        this.values1 = values1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.lblPage);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView textView1 = (TextView) rowView.findViewById(R.id.lblName);
        textView.setText(values.get(position));
        textView1.setText(values1.get(position));

        return rowView;
    }
}