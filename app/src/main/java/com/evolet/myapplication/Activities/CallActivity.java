package com.evolet.myapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.myapplication.Items.Services;
import com.evolet.myapplication.R;
import com.google.firebase.database.FirebaseDatabase;

public class CallActivity extends AppCompatActivity {

    private TextView n,a,f,ad,aad,pn,pp,d,s;
    Button c,update;
    Spinner us;
    String status;
    String[] sp = new String[]{"Request Rejected","Service Delivered","Request Accepted"};
    private static Services data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        data = (Services) bundle.getSerializable("data");
        n = findViewById(R.id.n);
        a = findViewById(R.id.a);
        f = findViewById(R.id.f);
        ad = findViewById(R.id.ad);
        aad = findViewById(R.id.aad);
        pn = findViewById(R.id.pn);
        pp = findViewById(R.id.pp);
        d = findViewById(R.id.d);
        s = findViewById(R.id.s);
        c = findViewById(R.id.c);
        us = findViewById(R.id.us);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        us.setAdapter(adapter);
        update = findViewById(R.id.update);
        n.setText("Name: "+data.getName());
        a.setText("Age: "+data.getAge());
        f.setText("Fee: "+data.getFee());
        ad.setText("Address: "+data.getAddress());
        aad.setText("Aadhar: "+data.getAadhar());
        pn.setText("ProductName: "+data.getProdname());
        pp.setText("ProductPrice: "+data.getProdprice());
        d.setText("Other Details: "+data.getDetails());
        s.setText("Status: "+data.getStatus());
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                Intent s = new Intent(Intent.ACTION_SENDTO);
                String m = "sms:" + data.getPhone();
                s.setData(Uri.parse(m));
                String p = "tel:" +data.getPhone(); ;
                i.setData(Uri.parse(p));
                Intent chooserIntent = Intent.createChooser(i, "Call or SMS");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { s });
                startActivity(chooserIntent);
            }
        });
        us.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = us.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(status)){
                    Toast.makeText(getApplicationContext(),"select status to update",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference("Services").child(data.getName())
                            .child("status").setValue(status);
                    Toast.makeText(getApplicationContext(),"Status updated Successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(this,RequestsActivity.class);
        startActivity(i);
    }
}
