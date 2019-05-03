package com.evolet.myapplication;



import android.widget.ImageView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

public class PicassoClient {
    public static void downloadImage(String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.get().load("https://www.androidhive.info/wp-content/uploads/2017/07/androidhive2x.png").placeholder(R.drawable.ic_launcher_background).into(img);
        }else {
            Picasso.get().load(R.drawable.ic_launcher_background).into(img);
        }
    }
}
