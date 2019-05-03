package com.evolet.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.evolet.myapplication.Activities.ItemActivity;
import com.evolet.myapplication.Adapters.AdminItemAdapter;
import com.evolet.myapplication.Adapters.ProductsAdapter;
import com.evolet.myapplication.Adapters.RecyclerItemClickListener;
import com.evolet.myapplication.Adapters.UserAdapter;
import com.evolet.myapplication.Adapters.UserRcAdapter;
import com.evolet.myapplication.Items.Materials;
import com.evolet.myapplication.Items.ProductItem;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    DatabaseReference ProductsRef;
    FirebaseDatabase mFirebaseDatabase;
    private RecyclerView recyclerView;
    ListView mListView;
    RecyclerView.LayoutManager layoutManager;
    List<Materials> materialsList;
    ProductItem productItem;
    SQLiteHandler mDbHandler;
    ArrayList<ProductItem> appList;
    ArrayList<String> url;
    String u;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_fragment, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String key = database.getReference("Products").getRef().getKey();
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Log.d("Medical Frag", "Random Key:  " + key);
        Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
        materialsList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ProductsRef = mFirebaseDatabase.getReference();


        try {
            mDbHandler = new SQLiteHandler(getActivity());
            appList = new ArrayList<>();
            url = new ArrayList<>();
            appList = mDbHandler.getAppList();
            productItem = appList.get(0);
            String xName, xPrice, xCategory, xUnit;
            xName = productItem.getProdName();
            xPrice = productItem.getProdPrice();
            xCategory = productItem.getProdCategory();
            xUnit = productItem.getUnit();
            final ArrayList<Object> headerList = new ArrayList<>();
            headerList.add("Product");
            headerList.addAll(appList);

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
     /*   for(int i=0;i<appList.size();i++){
            DatabaseReference ref = ProductsRef.child("Products").child(appList.get(0).getProdName())
                    .child("mImage");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    u = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            url.add(u);
        }*/
        UserRcAdapter adapter = new UserRcAdapter(getActivity(), appList,mDbHandler/*,url*/);
        RecyclerView medicinesListView = view.findViewById(R.id.product_rc);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        medicinesListView.setLayoutManager(llm);
        medicinesListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        adapter.notifyDataSetChanged();
        medicinesListView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView ,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                       /* Intent i=new Intent(getActivity(),ItemActivity.class);
                        String name = appList.get(position).getProdName();
                        Toast.makeText(getActivity(), ""+name, Toast.LENGTH_SHORT).show();
                        i.putExtra("ProdName",name);
                        startActivity(i);*/
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                }));
        return view;
    }

}
