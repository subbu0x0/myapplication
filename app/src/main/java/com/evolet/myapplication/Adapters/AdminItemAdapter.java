package com.evolet.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class AdminItemAdapter extends RecyclerView.Adapter<AdminItemAdapter.MyViewHolder> {
    private ArrayList<ProductItem> dataSet;
    Context mContext;
    ProductItem materials;
    SQLiteHandler mSqLiteHandler;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  prodName,prodPrice,prodUnit,prodCategory;
        ImageView ivDelete;
        ImageView prodImage;
        public MyViewHolder(View itemView){
            super(itemView);

            this. prodImage = itemView.findViewById(R.id.prodImage);
            this. prodName = itemView.findViewById(R.id.prodName);
            this.prodPrice = itemView.findViewById(R.id.prodPricePerUnit);
            this.prodUnit = itemView.findViewById(R.id.prodUnit);
            this.prodCategory=itemView.findViewById(R.id.tv_category);
        }
    }

    public AdminItemAdapter(Context mContext, ArrayList notificationList, SQLiteHandler mSqLiteHandler) {
        this.mContext=mContext;
        this.dataSet=notificationList;
        this.mSqLiteHandler=mSqLiteHandler;

    }
    @Override
    public int getItemCount() {
        ///   return dataSet == null ? 0 : dataSet.size();
        return  dataSet.size();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_rc_adapter, parent,
                false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final TextView prodName=holder.prodName;
        final  TextView prodPrice=holder.prodPrice;
        final  TextView prodCategory=holder.prodCategory;
         final TextView prodUnit=holder.prodUnit;
         ImageView prodImage=holder.prodImage;
        //materials=mProMaterialsList.get(position);
        materials=(ProductItem) dataSet.get(position);
        mSqLiteHandler=new SQLiteHandler(mContext);
        prodImage.setImageResource(R.mipmap.ic_launcher);
        prodName.setText(materials.getProdName());
      //  prodPrice.setText("Price: Rs."+materials.getProdPrice());
        prodUnit.setText("Id: "+materials.getUnit());
        prodCategory.setText(materials.getProdCategory());



    }

 /*   public void removeItem(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ProductItem mDriverMessageModel,int position){
        dataSet.add(position,mDriverMessageModel);
        notifyItemInserted(position);
    }
*/
}