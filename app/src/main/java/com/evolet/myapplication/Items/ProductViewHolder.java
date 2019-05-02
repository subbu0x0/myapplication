package com.evolet.myapplication.Items;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evolet.myapplication.Interface.ItemClickListner;
import com.evolet.myapplication.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice,textProductCategory;
    public ImageView imageView;
    public ItemClickListner listner;


    public ProductViewHolder(View itemView)
    {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
        textProductCategory=(TextView)itemView.findViewById(R.id.product_category);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
