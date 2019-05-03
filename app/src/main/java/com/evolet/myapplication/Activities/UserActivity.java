package com.evolet.myapplication.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evolet.myapplication.Adapters.ViewPagerAdapter;
import com.evolet.myapplication.Fragments.CareFragment;
import com.evolet.myapplication.Fragments.GroceryFragment;
import com.evolet.myapplication.Fragments.MedicinesFragment;
import com.evolet.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    Toolbar toolBar;
    //This is our viewPager
    private ViewPager viewPager;
    TextView addproductTv;
    //Fragments
    ImageView mLogoutImageview,mNotificationImageview;View mCustomView,mCart;
    MedicinesFragment mMedicinesFragment;
    GroceryFragment mGroceryFragment;
    CareFragment mCareFragment;
    String[] tabTitle={"Medicines","MediNeeds","service"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        viewPager =  findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        setupViewPager(viewPager);
        toolBar=findViewById(R.id.toolbar);
        toolBar.setTitle("EZcare - User");
        tabLayout =   findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
       // addproductTv=findViewById(R.id.addProductTextView);
        mLogoutImageview=(ImageView)findViewById(R.id.logout);
        mCart = (ImageView)findViewById(R.id.cart_item_imagview2);
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),BillingActivity.class);
                startActivity(i);
            }
        });
       //  addproductTv=findViewById(R.id.addProductTextView);
        mLogoutImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


       /* addproductTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, AddProduct.class);
                startActivity(intent);
            }
        });*/
        try
        {
            setupTabIcons();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mMedicinesFragment=new MedicinesFragment();
        mGroceryFragment=new GroceryFragment();
        mCareFragment=new CareFragment();
        adapter.addFragment(mMedicinesFragment,"Medicine");
        adapter.addFragment(mGroceryFragment,"MediNeeds");
        adapter.addFragment(mCareFragment,"Nursing");
        viewPager.setAdapter(adapter);
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(tabTitle[pos]);

        return view;
    }

    private void setupTabIcons()
    {

        for(int i=0;i<tabTitle.length;i++)


            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
    }
    public void onBackPressed() {
        //do nothing
    }

}

