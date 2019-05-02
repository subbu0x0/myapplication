package com.evolet.myapplication.application;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class App extends Application {

    private static App singleton;

    public static App getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // FirebaseInstanceId.getInstance().getToken();
        FirebaseApp.initializeApp(this);
    }
}
