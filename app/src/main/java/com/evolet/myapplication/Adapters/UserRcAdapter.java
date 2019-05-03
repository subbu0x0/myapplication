package com.evolet.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aquery.AQuery;
import com.bumptech.glide.Glide;
import com.evolet.myapplication.Fragments.ProductsFragment;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.MyAppGlideModule;
import com.evolet.myapplication.PicassoClient;
import com.evolet.myapplication.db.SQLiteHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.evolet.myapplication.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class UserRcAdapter extends RecyclerView.Adapter<UserRcAdapter.MyViewHolder> {
    private ArrayList<ProductItem> dataSet;
    Context mContext;
    ProductItem materials;
    SQLiteHandler mSqLiteHandler;
    ArrayList<String> u;
    String i;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mNotificationMessage,prodName,prodPrice,prodUnit,prodCategory;
        ImageView ivDelete;
        ImageView prodImage;
        public RelativeLayout viewBackground, viewForeground;
        public MyViewHolder(View itemView){
            super(itemView);
             //this.ivDelete=(ImageView)itemView.findViewById(R.id.iv_Del);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.viewForeground);

            this. prodImage = itemView.findViewById(R.id.prodImage);
            this. prodName = itemView.findViewById(R.id.prodName);
            this.prodPrice = itemView.findViewById(R.id.prodPricePerUnit);
            this.prodUnit = itemView.findViewById(R.id.prodUnit);
            this.prodCategory=itemView.findViewById(R.id.tv_category);
            this.ivDelete=itemView.findViewById(R.id.deleteProduct);
        }
    }

    public UserRcAdapter(Context mContext, ArrayList notificationList, SQLiteHandler mSqLiteHandler) {
        this.mContext=mContext;
        this.dataSet=notificationList;
        //this.u = url;
        this.mSqLiteHandler=mSqLiteHandler;

    }
    @Override
    public int getItemCount() {
        ///   return dataSet == null ? 0 : dataSet.size();
        return  dataSet.size();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rc_adapter, parent,
                false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
      //  final TextView tvChapterName = holder.mNotificationMessage;
        ImageView ivDelete=holder.ivDelete;
        final TextView prodName=holder.prodName;
        final  TextView prodPrice=holder.prodPrice;
        final  TextView prodCategory=holder.prodCategory;
         final TextView prodUnit=holder.prodUnit;
         ImageView prodImage=holder.prodImage;
        holder.prodImage.setVisibility(View.VISIBLE);
        //Picasso.get().load("https://www.androidhive.info/wp-content/uploads/2017/07/androidhive2x.png")
          //      .placeholder(R.drawable.ic_launcher_background)
               // .fit().centerCrop().into(prodImage);
        //materials=mProMaterialsList.get(position);
      //  Glide.with(mContext).load("https://www.androidhive.info/wp-content/uploads/2017/07/androidhive2x.png").into(holder.prodImage);
        materials=(ProductItem) dataSet.get(position);

        mSqLiteHandler=new SQLiteHandler(mContext);
        prodImage.setImageResource(R.mipmap.ic_launcher);
        prodName.setText(materials.getProdName());
        prodPrice.setText("Price: Rs."+materials.getProdPrice());
        prodUnit.setText("Id: "+materials.getUnit());
        prodCategory.setText(materials.getProdCategory());
        //PicassoClient p = new PicassoClient();
        //p.downloadImage(u.get(position),prodImage);
      //  StorageReference storageReference =  FirebaseStorage.getInstance().getReference().child("Product Images")
        //        .child(materials.getProdName());
// Load the image using Glide
      /* URL myurl = null;
        try {
            myurl = new URL(i);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
       /* Context context = holder.prodImage.getContext();
        Glide.with(context)
                .load(i)
                .into(holder.prodImage );
      Picasso.get().load(i).into(prodImage);
        Context context = holder.prodImage.getContext();
        AQuery aq=new AQuery(context); // intsialze aquery
        aq.id(R.id.prodImage).image(i);*/

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String s=tvChapterName.getText().toString();
                String s=prodName.getText().toString();

                mSqLiteHandler.removeSingleContent(s);
                removeItem(position);
                notifyItemRemoved(position);
                Toast.makeText(mContext,  " Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeItem(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ProductItem mDriverMessageModel,int position){
        dataSet.add(position,mDriverMessageModel);
        notifyItemInserted(position);
    }

}