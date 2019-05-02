package com.evolet.myapplication.Activities;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.evolet.myapplication.R;

import java.text.SimpleDateFormat;

public class InvoiceActivity extends AppCompatActivity {

    long date = System.currentTimeMillis();

    SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy");
    String dateString = sdf.format(date);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView name,phone,address,date,tp;
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);
        tp = findViewById(R.id.tp);
        Bundle b = getIntent().getExtras();
        String n,p,add,total;
        n = b.getString("name");
        p = b.getString("phone");
        add = b.getString("address");
        total  = b.getString("total");
        date.setText(dateString);
        name.setText(n);
        phone.setText(p);
        address.setText(add);
        tp.setText(total);
    }

}
