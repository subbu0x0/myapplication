package com.evolet.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.myapplication.Items.UserDetails;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {
EditText mUserName,muserPhoneNo,mUserAddress;
Button saveAddress,invoice;
LinearLayout mAdressLayout;
String mUser,mPhone,mAddress,total;
SQLiteHandler mSqLiteHandler;
    ArrayList<UserDetails> addressList;
    UserDetails mUserDetail;
    String wUserName,wPhoneNo,wAdddress;
    TextView mDeliveryDay;
    Button b;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mUserName=findViewById(R.id.etName);
        muserPhoneNo=findViewById(R.id.etphone);
        mUserAddress=findViewById(R.id.etAddress);
        saveAddress=findViewById(R.id.confirmAdress);
        mDeliveryDay=findViewById(R.id.mDeliveryDay);
        mSqLiteHandler=new SQLiteHandler(this);
        saveAddress.setText("Confirm Address");
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser=mUserName.getText().toString();
                mPhone=muserPhoneNo.getText().toString();
                mAddress=mUserAddress.getText().toString();
                if (mUser.isEmpty() | mPhone.isEmpty()|mAddress.isEmpty()) {
                    Toast.makeText(AddressActivity.this, "phone or address is empty", Toast.LENGTH_SHORT).show();

                }else {

                    mSqLiteHandler.addUserDetail(mUser,mPhone,mAddress);
                    invoice = findViewById(R.id.invoice);
                    invoice.setVisibility(View.VISIBLE);
                    saveAddress.setText("Check Out");
                    mDeliveryDay.setText("Your order will deliver soon");
                    /*Intent intent=new Intent(getApplicationContext(),BillingActivity.class);
                    startActivity(intent);*/
                }
            }
        });
        Bundle b = getIntent().getExtras();
        total =b.getString("total");
    }
    public void inv(View view){
        Intent i=new Intent(getApplicationContext(),InvoiceActivity.class);
        Bundle b = new Bundle();
        b.putString("total",total);
        b.putString("name",mUser);
        b.putString("phone",mPhone);
        b.putString("address",mAddress);
        i.putExtras(b);
        startActivity(i);
    }
    public void home(View view){
    Intent i = new Intent(getApplicationContext(),UserActivity.class);
    startActivity(i);
    }
}

