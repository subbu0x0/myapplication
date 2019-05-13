package com.evolet.myapplication.CredentialActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evolet.myapplication.Activities.SplashActivity;
import com.evolet.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        EditText n,e,p,p1;
        n = findViewById(R.id.name);
        e = findViewById(R.id.mail);
        p = findViewById(R.id.pwd);
        p1 = findViewById(R.id.pwd1);
        Button r,l;
        r= findViewById(R.id.reg);
        l = findViewById(R.id.login);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(i);
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(n.getText().toString())||TextUtils.isEmpty(e.getText().toString())||TextUtils.isEmpty(p.getText().toString())||TextUtils.isEmpty(p1.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Enter the details",Toast.LENGTH_LONG).show();
                }
                else{
                    String name = n.getText().toString();
                    String mail = e.getText().toString();
                    String pwd = p.getText().toString();
                    String pwd1 = p1.getText().toString();
                    if(!pwd.equals(pwd1)){
                        Toast.makeText(getApplicationContext(),"Password donot match",Toast.LENGTH_LONG).show();
                    }
                    else{
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterUser.this, "sign up error", Toast.LENGTH_SHORT).show();
                                } else {
                                    String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id).child("name");
                                    current_user_db.setValue(mail);
                                    Toast.makeText(getApplicationContext(),"registration successful",Toast.LENGTH_LONG)
                                            .show();
                                    n.setText("");
                                    e.setText("");
                                    p.setText("");
                                    p1.setText("");
                                    FirebaseAuth.getInstance().signOut();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
