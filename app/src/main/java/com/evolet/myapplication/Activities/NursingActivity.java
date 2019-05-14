package com.evolet.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.evolet.myapplication.Items.Services;
import com.evolet.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class NursingActivity extends AppCompatActivity {
    EditText n,ad,mn,an,a,d;
    String name,addr,phone,age,fee,details,aid,pname,pprice;
    RadioButton rb;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing);
        n = findViewById(R.id.name);
        ad = findViewById(R.id.addr);
        mn = findViewById(R.id.pn);
        an = findViewById(R.id.id);
        a = findViewById(R.id.age);
        d = findViewById(R.id.details);
        rg= findViewById(R.id.rg);
        Bundle b = getIntent().getExtras();
        pname = b.getString("ProdName");
        pprice = b.getString("ProdPrice");
    }
    public void submit(View v){
        name = n.getText().toString();
        addr = ad.getText().toString();
        phone = mn.getText().toString();
        age = a.getText().toString();
        aid = an.getText().toString();
        details = d.getText().toString();
        int sid = rg.getCheckedRadioButtonId();
        rb = findViewById(sid);
        fee = rb.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(addr)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(age)||
                TextUtils.isEmpty(aid)||TextUtils.isEmpty(fee)){
            Toast.makeText(this,"Enter Valid Details",Toast.LENGTH_LONG)
                    .show();
        }
        else{
            if(TextUtils.isEmpty(details)){
                details = "";
            }
            Services services = new Services(name,phone,addr,aid,age,details,fee,pname,pprice,"In process");
            FirebaseDatabase.getInstance().getReference("Services").child(name).setValue(services).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"Service Requested Successfully, we will contact you within 12 hours",
                            Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
