package com.evolet.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.evolet.myapplication.Items.Services;
import com.evolet.myapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    ArrayList<Services> s;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Services> objects) {
        super(context, resource, objects);
        this.s = objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_item, null);
        TextView textView = v.findViewById(R.id.na);
        TextView textView1 = v.findViewById(R.id.pr);
        textView.setText("Requestor : "+s.get(position).getName());
        textView1.setText("Product Name : "+s.get(position).getProdname());
        return v;

    }
}
