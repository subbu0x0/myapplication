package com.evolet.myapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.evolet.myapplication.Adapters.CustomAdapter;
import com.evolet.myapplication.Items.Services;
import com.evolet.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestsActivity extends AppCompatActivity {
    ArrayList<Services> data = new ArrayList<>();
    ListView lv;
    CustomAdapter c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        lv = findViewById(R.id.lv);
        //Toast.makeText(this,"requests",Toast.LENGTH_LONG).show();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Services");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    data.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        Services s = ds.getValue(Services.class);
                        if(s.getStatus().equals("In process")||s.getStatus().equals("Request Accepted")){
                            data.add(s);
                        }
                    }
                    c = new CustomAdapter(getApplicationContext(),R.layout.list_item,data);
                    lv.setAdapter(c);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database","Error");
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CallActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data.get(position) );
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(this,AdminActivity.class);
        startActivity(i);
    }

}
