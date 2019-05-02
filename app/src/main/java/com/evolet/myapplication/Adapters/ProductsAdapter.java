package com.evolet.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evolet.myapplication.Items.Materials;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends ArrayAdapter<ProductItem> {
    ProductItem materials;
    Context context;
    Activity mActivity;
    ArrayList<ProductItem> mProMaterialsList;
    SQLiteHandler mSqLiteHandler;

    public ProductsAdapter(Activity context, ArrayList<ProductItem> productItems){
        super(context, R.layout.products_items,  productItems);
        this.mActivity = context;
        this.mProMaterialsList = productItems;
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater mLayoutInflater=mActivity.getLayoutInflater();
        View rootView = mLayoutInflater.inflate(R.layout.products_items,null,true);

        ImageView prodImage = (ImageView)rootView.findViewById(R.id.prodImage);
        TextView prodName = (TextView)rootView.findViewById(R.id.prodName);
        TextView prodPrice = (TextView)rootView.findViewById(R.id.prodPricePerUnit);
        TextView prodUnit = (TextView)rootView.findViewById(R.id.prodUnit);
        TextView prodCategory=rootView.findViewById(R.id.tv_category);
          materials=mProMaterialsList.get(position);
        prodImage.setImageResource(R.mipmap.ic_launcher);
        prodName.setText(materials.getProdName());
        prodPrice.setText("Price: Rs."+materials.getProdPrice());
        prodUnit.setText("Unit: "+materials.getUnit());
        prodCategory.setText(materials.getProdCategory());
        mSqLiteHandler=new SQLiteHandler(getContext());
        /*mImgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSqLiteHandler.removeSingleContent(materials.getProdName());
                notifyDataSetChanged();
                updateResults(mProMaterialsList);

            }
        });*/

        return rootView;
    }
    public void updateResults(ArrayList<ProductItem> results) {
        // assign the new result list to your existing list it will work
        notifyDataSetChanged();
    }
}
