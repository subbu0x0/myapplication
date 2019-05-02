package com.evolet.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evolet.myapplication.CredentialActivities.AdminLoginActivity;
import com.evolet.myapplication.CredentialActivities.UserLoginActivity;
import com.evolet.myapplication.R;
import com.google.firebase.FirebaseApp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class SplashActivity extends AppCompatActivity {

    private Button mDriver, mCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);
        mDriver = (Button) findViewById(R.id.admin);
        mCustomer = (Button) findViewById(R.id.user);

        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SplashActivity.this, AdminLoginActivity.class);
               // Intent intent = new Intent(SplashActivity.this, AdminActivity.class);
                startActivity(intent);
              //  finish();
             //   return;
            }
        });

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SplashActivity.this, UserLoginActivity.class);
               // Intent intent = new Intent(SplashActivity.this, UserActivity.class);
                startActivity(intent);
            //    finish();
             //   return;
            }
        });
    }
}

