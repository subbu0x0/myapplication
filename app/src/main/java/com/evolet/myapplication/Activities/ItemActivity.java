package com.evolet.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    String pos;
    String productCategory;
    SQLiteHandler mSqLiteHandler;
    ArrayList<ProductItem> appList;
    int priced;
    ArrayList<ProductItem> cartList;
    ProductItem mProductItem,mCartItem;
    String xName, xPrice, xCategory, xUnit;
    TextView mProductName, mProdctPrice, mProductQuantity;
    ImageView mProductImage;
    TextView tv_increment, tv_decrement;
    int i = 1;
    int j = 0;
    int price = 0;
Button btnCart;
    String yName,yPrice,yCategory,yUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

   /*     i.putExtra("mProductName",materials.getProdName());
        i.putExtra("mProductPrice",materials.getProdPrice());
        i.putExtra("mProductUnit",materials.getUnit());
        i.putExtra("mProductCategory",materials.getProdCategory());*/


        mProductName = findViewById(R.id.tvname);
        mProdctPrice = findViewById(R.id.tv_price);
        mProductQuantity = findViewById(R.id.tvunit);
        tv_increment = findViewById(R.id.tv_increment);
        tv_decrement = findViewById(R.id.tv_decrement);
        mProductImage = findViewById(R.id.health_iv);
        btnCart=findViewById(R.id.addToCart);
        xName = getIntent().getExtras().getString("ProdName");
        btnCart.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
      /*  xPrice = getIntent().getExtras().getString("mProductPrice");
        xCategory = getIntent().getExtras().getString("mProductCategory");
        xUnit = getIntent().getExtras().getString("mProductUnit");
*/
      btnCart.setVisibility(View.INVISIBLE);
        mSqLiteHandler = new SQLiteHandler(this);
        try {
            appList=new ArrayList<>();
            appList=mSqLiteHandler.getProductItem(xName);
            mProductItem=appList.get(0);
            String xName,xPrice,xCategory,xUnit;
            yName=mProductItem.getProdName();
            yPrice=mProductItem.getProdPrice();
            yCategory=mProductItem.getProdCategory();
            yUnit=mProductItem.getUnit();
            mProductName.setText(yName);
            mProdctPrice.setText("Rs."+yPrice);

            btnCart.setVisibility(View.VISIBLE);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        boolean check=mSqLiteHandler.checkExists(yName);
        try{
            if(check)
            {
                btnCart.setVisibility(View.VISIBLE);
                cartList=new ArrayList<>();
                cartList=mSqLiteHandler.showCartItem(yName);
                mCartItem=cartList.get(0);
                String totalVal=mCartItem.getProdPrice();
                String qua=mCartItem.getUnit();
                String name=mCartItem.getProdName();
                String cat=mCartItem.getProdCategory();
                btnCart.setText(qua+"Item added"+" Added to Cart"+"      Rs."+totalVal);


                Toast.makeText(getBaseContext(), "exist", Toast.LENGTH_SHORT).show();
            }else{

            }

        }catch (Exception e){
            e.printStackTrace();
        }
                //appList=mSqLiteHandler.getProductDetails(number);
        //mProductItem=appList.get(1);


        mProductName.setText(yName);
        mProdctPrice.setText("Rs: " + yPrice);
        price = Integer.valueOf(yPrice);
        mProductQuantity.setText("" + i);

        if (yCategory.equals("Medicine")) {
            mProductImage.setImageResource(R.drawable.medcine);
        }
        if (yCategory.equals("MediNeeds")) {
            mProductImage.setImageResource(R.drawable.grocery);
        }
        if (yCategory.equals("Nursing")) {
            mProductImage.setImageResource(R.drawable.healthservice);
        }


    }

    public void increaseInteger(View view) {
        btnCart.setVisibility(View.VISIBLE);
        j = j + 1;
        if (j>5){
            j=5;
        }
        display(j);

    }

    public void decreaseInteger(View view) {
        btnCart.setVisibility(View.VISIBLE);
        j--;
        if (j < 0) {
            j = 0;
        }
        display(j);
    }

    private void display(int number) {
        mProductQuantity = findViewById(R.id.tvunit);
        mProductQuantity.setText("" + number);
        btnCart.setVisibility(View.VISIBLE);
          priced = j * price;
        btnCart.setText(j+"   Items"+"  Add to Cart"+    "     RS."+priced);
    }

    public void addToCart(View view) {

        String totalval = priced + "" + yName + " Quantity: " + j;
        Toast.makeText(this, ""+totalval, Toast.LENGTH_SHORT).show();

       boolean DBCArtcheck=false;
        DBCArtcheck=mSqLiteHandler.checkExists(yName);
       /*if (DBCArtcheck){
           mSqLiteHandler.updateData(yName,j,priced,yCategory);
           Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
       }else {*/
           mSqLiteHandler.addToCart(yName,j,priced,yCategory);
           Toast.makeText(this, "newly added", Toast.LENGTH_SHORT).show();
      // }

        btnCart.setText(j+"Items"+" Add to Cart"+priced);

        Intent i=new Intent(getApplicationContext(),BillingActivity.class);
        startActivity(i);
        //boolean addessCheck=mSqLiteHandler.CheckIsDataAlreadyInDBorNot("userDetail","username","userphone","useraddress")
    }
}
