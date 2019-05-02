package com.evolet.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.myapplication.Activities.ItemActivity;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<ProductItem> {


    ProductItem materials;
    Context context;
    Activity mActivity;
    ArrayList<ProductItem> mProMaterialsList;
    SQLiteHandler mSqLiteHandler;

    public UserAdapter(Activity context, ArrayList<ProductItem> productItems){
        super(context, R.layout.user_item_adapter,  productItems);
        this.mActivity = context;
        this.mProMaterialsList = productItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mLayoutInflater=mActivity.getLayoutInflater();
        View rootView = mLayoutInflater.inflate(R.layout.user_item_adapter,null,true);
        CardView mCardView=rootView.findViewById(R.id.card_layout);
        ImageView prodImage = (ImageView)rootView.findViewById(R.id.prodImage);
        TextView prodName = (TextView)rootView.findViewById(R.id.prodName);
        TextView prodPrice = (TextView)rootView.findViewById(R.id.prodPricePerUnit);
        TextView prodUnit = (TextView)rootView.findViewById(R.id.prodUnit);
        TextView prodCategory=rootView.findViewById(R.id.tv_category);
        ImageView cart_item_imagview=rootView.findViewById(R.id.cart_item_imagview);
        materials=mProMaterialsList.get(position);
        prodImage.setImageResource(R.mipmap.ic_launcher);
        prodName.setText(materials.getProdName());
        prodPrice.setText("Price: Rs."+materials.getProdPrice());
        prodUnit.setText("Unit: "+materials.getUnit());
        prodCategory.setText(materials.getProdCategory());

        mSqLiteHandler=new SQLiteHandler(getContext());

    /*    mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mActivity, ItemActivity.class);

                i.putExtra("mProductName",materials.getProdName());
                i.putExtra("mProductPrice",materials.getProdPrice());
                i.putExtra("mProductUnit",materials.getUnit());
                i.putExtra("mProductCategory",materials.getProdCategory());
                Log.d("PO+++++" ,""+materials.getProdName()+
                        materials.getProdPrice()+
                        materials.getUnit()+
                        materials.getProdCategory());
                mActivity.startActivity(i);

            }
        });*/
        /*cart_item_imagview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int p=0;
                p++;
                String price = materials.getProdPrice();
                int productPrice = Integer.parseInt(price);
                int prod= p*productPrice;
                mSqLiteHandler.addToCart(materials.getProdName(),
                       p ,prod,materials.getProdCategory());
                Toast.makeText(mActivity, ""+materials.getProdName()+
                        "Quantity : "+ p +
                        "Price : "+ prod+
                        "Category : "+ materials.getProdCategory(), Toast.LENGTH_SHORT).show();
            }
        });*/

        return rootView;
    }

}
