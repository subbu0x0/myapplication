package com.evolet.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.evolet.myapplication.Adapters.ProductsAdapter;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {

    SQLiteHandler mSqLiteHandler;
    ArrayList<ProductItem> appList,totalResut;
    ProductItem mProductItem,resulItem;
    String val;
    String xName,xPrice,xCategory,xUnit;
    ListView mListView;
    TextView mTotal;
    Button Confirm;
    int total =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
          mListView=findViewById(R.id.bill_list);
          Confirm=findViewById(R.id.confirm_address);
mTotal=findViewById(R.id.total_val);
        mSqLiteHandler = new SQLiteHandler(this);
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
        try {
            totalResut=new ArrayList<>();
            totalResut=mSqLiteHandler.Caclutate();
            resulItem  =totalResut.get(0);
            val=resulItem.getProdPrice();
            //mTotal.setText(val);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < appList.size(); i++) {
                total = total + Integer.valueOf(appList.get(i).getProdPrice());
                mTotal.setText(String.valueOf(total));
            }
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    ProductsAdapter mProductsAdapter=new ProductsAdapter(this,appList);
        mListView.setAdapter(mProductsAdapter);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AddressActivity.class);
                Bundle b = new Bundle();
                b.putString("total",String.valueOf(total));
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
