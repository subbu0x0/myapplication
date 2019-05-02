package com.evolet.myapplication.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.evolet.myapplication.Adapters.ProductsAdapter;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.Items.UserDetails;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    SQLiteHandler mSqLiteHandler;
    ArrayList<ProductItem> appList,totalResut;
    ArrayList<UserDetails> mAddressList;
    UserDetails mUserItem;
    ProductItem mProductItem,resulItem;
    String val;
    String xName,xPrice,xCategory,xUnit;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_activity_);
        mSqLiteHandler = new SQLiteHandler(this);
        TextView mText=findViewById(R.id.address);
        try {
            mSqLiteHandler = new SQLiteHandler(this);
            appList = new ArrayList<>();
            appList=new ArrayList<>();
            appList=mSqLiteHandler.showCartItem();
            mProductItem=appList.get(0);

            xName=mProductItem.getProdName();
            xPrice=mProductItem.getProdPrice();
            xCategory=mProductItem.getProdCategory();
            xUnit=mProductItem.getUnit();
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        mAddressList=new ArrayList<>();
        mAddressList=mSqLiteHandler.showUserDetail();
        mUserItem=mAddressList.get(0);
        String User=mUserItem.getmUserName();
        String phone=mUserItem.getmPhone();
        String add=mUserItem.getmAddress();
     //   mText.setText("Name: "+User+" Phone No:  "+phone+ " address:  "+add);
        /*final ArrayList<Object> headerList = new ArrayList<>();
        headerList.add("Name: "+User+" Phone No:  "+phone+ "address:  "+add);
        headerList.addAll(appList);*/
        mListView=findViewById(R.id.your_orders);
        ProductsAdapter mProductsAdapter=new ProductsAdapter(this,appList);
        mListView.setAdapter(mProductsAdapter);
    }

}
